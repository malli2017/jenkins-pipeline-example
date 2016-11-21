#!/usr/bin/env groovy

echo 'Hello from Pipeline'
echo version()
env.PATH = "${tool 'Maven 3'}/bin:${env.PATH}"

stage 'Build', {
    node {
        checkout scm
        sh 'mvn clean install -DskipTests'
    }
}

node {
    parallel(
            unittests: {
                stage 'Unit tests', {
                    sh 'mvn -Punit-tests test'
                    step([$class: 'JUnitResultArchiver', testResults: '**/target/surefire-reports/TEST-*.xml'])
                }
            },
            featuretests: {
                stage 'Feature tests', {
                    sh 'mvn -Pintegration-tests test'
                    step($class: 'CucumberTestResultArchiver', testResults: 'target/cucumber-report.json')
                }
            }
    )
}


stage 'Deploy to nexus', {
    timeout(time: 5, unit: 'DAYS') {
        input message: 'Approve deployment?'
    }
    node {
// sh 'mvn deploy'\
        echo 'Deployed! :)'
    }
}


def version() {
    def matcher = readFile('pom.xml') =~ '<version>(.+)</version>'
    matcher ? matcher[0][1] : null
}