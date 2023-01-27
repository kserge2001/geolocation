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
        stage('UNIT Testing') {
            steps{
                sh 'mvn test'
            }
        }
        stage('Integration Testing') {
            steps{
                script{
                    sh 'mvn verify -DskipUnitTests'
                }
            }
        }
        stage('Maven Build'){
            steps{
                sh 'mvn clean install package'
            }
        }
        stage('Build & SonarQube Analysis #1'){
            steps {
                agent {
                docker {image 'maven:3.8.6-openjdk-11-slim' }
                sh 'mvn sonar:sonar -Dsonar.projectKey=henrykrop2022_geolocation-23 -Dsonar.java.banaries=.'
                }
            }   
            
        }
        stage('Quality Gate'){
            steps{
                script{
                    timeout(time: 1, unit: 'HOURS') {
                        waitForQualityGate abortPipeline: false, credentialsId: 'sonarqube-token'
                    }

                }
            }
        }
    }
}
    

