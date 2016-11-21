#!/usr/bin/env groovy
node 'master', {
    echo 'Hello from Pipeline'
    echo version()
    env.PATH = "${tool 'Maven 3'}/bin:${env.PATH}"

    checkout scm

    stage 'Build', {
        sh 'mvn clean install -DskipTests'
    }

    parallel(
            "Unit tests": {
                stage 'Unit tests', {
                    sh 'mvn -Punit-tests test'
                    step([$class: 'JUnitResultArchiver', testResults: '**/target/surefire-reports/TEST-*.xml'])
                }
            },
            "Feature tests": {
                stage 'Feature tests', {
                    sh 'mvn -Pintegration-tests test'
                    step($class: 'CucumberTestResultArchiver', testResults: 'target/cucumber-report.json')
                }
            }
    )
}

stage 'Deploy to nexus', {
    // sh 'mvn deploy'
}



def version() {
    def matcher = readFile('pom.xml') =~ '<version>(.+)</version>'
    matcher ? matcher[0][1] : null
}