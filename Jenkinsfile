pipeline {
    agent any
    tools {
        maven 'M2_HOME'
    }
    stages {
        stage("maven build") {
            steps{
                sh 'mvn clean install package'
            }

        }
        stage("check pwd") {
            steps{
                sh 'pwd'
            }

        }
        stage("list the dir") {
            steps{
                sh 'ls'
            }

        }
    }
}