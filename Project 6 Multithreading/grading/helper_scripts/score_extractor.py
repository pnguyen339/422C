#!/usr/bin/python
# -*- coding: UTF-8 -*-

import xml.etree.ElementTree as parser
import sys
import os.path

def get_tescase_weights():
    weights = {}
    weights['testBestSeatEmpty'          ] = 1
    weights['testBestSeatDouble'         ] = 1
    return weights;


def get_failed_tests(tree):
    result = []
    for t in tree.findall('testcase'):
        if len(t.findall('error')) + len(t.findall('failure')) > 0:
            result.append(t.attrib['name'])
    return result

def calculate_grade(weights, failures):
    total = 0
    loss = 0
    for w in weights:
        total += weights[w]
    for f in failures:
        loss += weights[f]
    return total - loss

def main(argv):
    if len(argv) < 3:
        print 'too few arguments'
        exit(1)
    path = argv[1]
    submission_name=argv[2].replace('/', '').strip()
    if os.path.exists(path):
        tree = parser.parse(path).getroot()
        weights = get_tescase_weights()
        ftests = get_failed_tests(tree)
        if len(ftests) is 1 and ftests[0]=='assignment6.A6Test':
            grade = 0
        else:
            grade = calculate_grade(weights, ftests)
        print '"'+submission_name+'", ', grade, ', ""'
    else:
        print '"'+submission_name+'", ', 0, ', "compile_error"'

main(sys.argv)
