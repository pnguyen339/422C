#!/bin/bash

function try() { [[ $- = *e* ]]; SAVED_OPT_E=$?;set +e;}
function throw() { exit $1;}
function catch() { export ex_code=$?;(( $SAVED_OPT_E )) && set +e;return $ex_code;}
function enable_throwing_errors() { set -e;}
function disable_throwing_errors() { set +e;}

main_dir="$(pwd)"
submissions_dir="$main_dir/submissions"
grading_project_original="$main_dir/grading_project_original"
grading_project="$main_dir/grading_project"
detailed_feedback_dir="$main_dir/detailed_feedback"
brief_feedback_file="$main_dir/brief_results.csv"

TA_MODE=false

collect_results()
{
	mkdir -p "$detailed_feedback_dir"
	cd "$submissions_dir"
	for tested_submission in */
	do
		tested_submission="${tested_submission:0:${#tested_submission}-1}"
        if [ -f "$tested_submission/test_log.txt" ]
        then
		    brief_result_line=$( python2.6 "$main_dir/helper_scripts/score_extractor.py" "$tested_submission/TEST-assignment6.A6Test.xml" "$tested_submission" )
		    echo "$brief_result_line" >> "$brief_feedback_file"
        fi
        
		zip -ry9Tm "$tested_submission" "$tested_submission" > /dev/null
		mv "$tested_submission".zip "$detailed_feedback_dir"

	done
	cd "$main_dir"
}

perform_test_on_this_submission()
{
    enable_throwing_errors

	this_submission_dir="$(pwd)"
	java_files="$(find . -name '*.java')"

	rm -rf "$grading_project"
    cp -R "$grading_project_original" "$grading_project"
    for jfile in $java_files
    do
	    mv "$jfile" "$grading_project/src/main/java/assignment6/"
    done

	cd "$grading_project"
    if [ "$TA_MODE" = true ]
    then
	    mvn clean test > test_log.txt 2>&1 || true
    else
	    mvn clean test 2>&1 | tee test_log.txt || true
    fi

	cd "$this_submission_dir"

	rm -rf *

	mv "$grading_project/test_log.txt" .

    if ls "$grading_project/target/surefire-reports/"*.xml 1> /dev/null 2>&1; then
        mv "$grading_project/target/surefire-reports/"*.xml .
    fi
}

run_test_on_submissions()
{
    enable_throwing_errors

	cd "$submissions_dir"

    if ( ls *.zip 1> /dev/null 2>&1 )
    then
	    for submission_raw_name in *.zip
	    do
	    	submission_name="${submission_raw_name%%.*}"
            try
            (
	    	    echo "### going on: " "$submission_name"

	    	    mkdir "$submission_name"
	    	    cd "$submission_name"
	    	    unzip ../"$submission_raw_name" > /dev/null
	    	    find . -name __MACOSX -exec rm -rfv {} \; > /dev/null 2>&1 || echo ""
	    	    rm ../"$submission_raw_name"

	    	    perform_test_on_this_submission

	    	    cd "$submissions_dir"
            )
            catch ||
            {
                echo "###### submission format error on $submission_name"
                echo "\"$submission_name\", 0, \"submission_format_error\"" >> "$brief_feedback_file"
                cd "$submissions_dir"
            }
	    done
    fi

	cd "$main_dir"
}

prepare_maven_idempotent()
{
    if [ -f ~/.profile ]
    then
        . ~/.profile
    fi

    if ! type "mvn" > /dev/null 2>&1 # if maven is not loaded
    then
        module load maven
    fi
}

main()
{
    enable_throwing_errors

    rm -rf "$grading_project"
    cp -r "$grading_project_original" "$grading_project"

	if [ ! -f "$brief_feedback_file" ]
	then
		echo '"submission_name", "grade", "comment"' > "$brief_feedback_file"
	fi

    prepare_maven_idempotent
	run_test_on_submissions
	echo "collecting results ..."
	collect_results
	echo "finished"

    disable_throwing_errors
}

main
