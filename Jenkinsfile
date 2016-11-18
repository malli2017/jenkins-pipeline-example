#!/usr/bin/env groovy

node {
    echo 'Hello from Pipeline'

    env.PATH = "${tool 'Maven 3'}/bin:${env.PATH}"

    stage 'Build and test'
    sh 'mvn clean package'

    stage 'Deploy to nexus'
    sh 'mvn deploy'
}

