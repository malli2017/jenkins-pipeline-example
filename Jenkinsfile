#!/usr/bin/env groovy

echo 'Hello from Pipeline'
env.PATH = "${tool 'Maven 3'}/bin:${env.PATH}"

stage 'Build', {
    node {
        checkout scm
        sh 'mvn clean install -DskipTests'
    }
}

node {
    parallel(
            "Unit tests": {
                stage 'Unit tests', {
                    sh 'mvn -Punit-tests test'
                    sleep(15)
                    step([$class: 'JUnitResultArchiver', testResults: '**/target/surefire-reports/TEST-*.xml'])
                }
            },
            "Feature tests": {
                stage 'Feature tests', {
                    sh 'mvn -Pintegration-tests test'
                    sleep(10)
                    step($class: 'CucumberTestResultArchiver', testResults: 'target/cucumber-report.json')
                }
            }
    )
}

stage 'Deploy to nexus', {
    node {
        // sh 'mvn deploy'
    }
}

