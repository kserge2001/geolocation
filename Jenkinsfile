pipeline {
    agent any
     tools {
  maven 'M2_HOME'
     }

    stages{
        stage('Git Checkout'){
            steps{
                git branch: 'main', url: 'https://github.com/henrykrop2022/geolocation-23.git'
            }
        }
        stage('UNIT Testing'){
            steps{
               sh 'mvn test'
               
            }
        }
        stage('Integration Testing'){
            steps{
               sh 'mvn verify -DskipUnitTests'
               
            }
        }
        stage('Maven Build'){
            steps {
                sh 'mvn clean install'
            }
        }
        stage ('SonarQube analysis'){
            steps {
                script {
                    withSonarQubeEnv {
                        sh 'mvn clean package sonar:sonar'
                    }
                }  
            }
        }
        stage ('Quality Gate Status'){
            steps {
                script {
                    waitForQualityGate abortPipeline: true
                }
            }   
        }
    }
}