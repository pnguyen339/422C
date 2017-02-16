#!/bin/bash

main_dir=$(pwd)
submissions_dir="$main_dir/submissions"
tests_dir="$main_dir/test_cases"
brief_feedback_file="$main_dir/brief_results.csv"
detailed_feedback_dir="$main_dir/detailed_feedback"

collect_results()
{
	mkdir -p "$detailed_feedback_dir"
	cd "$submissions_dir"
	for tested_submission in */
	do
		tested_submission=${tested_submission:0:${#tested_submission}-1}
		zip -ry9Tm "$tested_submission" "$tested_submission" > /dev/null
		mv "$tested_submission".zip "$detailed_feedback_dir"

	done
	cd "$main_dir"
}

perform_test_on_this_submission()
{
	this_dir=$(pwd)
    total_num=0
    correct_num=0
    submission_name="${PWD##*/}"
    for t_dir in "$tests_dir"/*/
    do
        cp "$t_dir/GameConfiguration.java" assignment2/
        cp "$t_dir/SecretCodeGenerator.java" assignment2/
        cd assignment2
        javac *.java 1> /dev/null 2>&1 &&
            total_num=$(($total_num+1)) || echo "compile error"
        cd ..
        touch temp_output.txt
        java assignment2.Driver < "$t_dir/input.txt" > temp_output.txt 2>&1 || echo "nothing" > /dev/null
        sed '/^\s*$/d' temp_output.txt > actual_output.txt
        sed '/^\s*$/d' "$t_dir/expected_output.txt" > temp_output.txt
        diff -uN temp_output.txt actual_output.txt >> output_diff.txt &&
            correct_num=$(($correct_num+1)) || echo "error on $t_dir"
    done
    rm -rf assignment2 temp_output.txt actual_output.txt
    echo "\"$submission_name\", \"$correct_num\", \"$total_num\"" >> "$brief_feedback_file"
}

run_test_on_submissions()
{
	cd "$submissions_dir"

    if ( ls *.zip 1> /dev/null 2>&1 )
    then
	    for submission_raw_name in *.zip
	    do
	    	submission_name="${submission_raw_name%%.*}"
            echo ""
	    	echo "### going on: " "$submission_name"

            unzip "$submission_raw_name" > /dev/null
            rm "$submission_raw_name"
	    	mkdir "$submission_name"
            mv "assignment2" "$submission_name"
            cd "$submission_name"

	    	perform_test_on_this_submission

	    	cd "$submissions_dir"
	    done
    fi

	cd "$main_dir"
}

main()
{
	set -e

	rm -rf  "$detailed_feedback_dir"
    echo '"submission_name", "passed_tests", "total_tests"' > "$brief_feedback_file"

	run_test_on_submissions
	echo "collecting results ..."
	collect_results
	echo "finished"

	set +e
}

main
