pipeline {
    agent any
    tools {
  maven 'M2_HOME'
    }
        triggers {
      pollSCM '* * * * *'
        }
             
    stages {
        stage('maven package ') {
            steps {
                sh 'mvn clean'
                sh 'mvn install'
                sh 'mvn package'
            }
        }
          stage('test') {
            steps {
                sh 'mvn test'

            }
        }
          stage('test') {
            steps {
                echo 'test'
              
            }
        }
          stage('deploy') {
            steps {
                echo 'deploy'
               
            }
        }
    }
}    