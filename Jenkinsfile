#!/usr/bin/env groovy

node {
    echo 'Hello from Pipeline'
    echo version()
    env.PATH = "${tool 'Maven 3'}/bin:${env.PATH}"

    checkout scm

    stage 'Build and test'
    sh 'mvn -Dmaven.test.failure.ignore clean verify'
    step([$class: 'JUnitResultArchiver', testResults: '**/target/surefire-reports/TEST-*.xml'])

    stage 'Deploy to nexus'
    sh 'mvn deploy'
}