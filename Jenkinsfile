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
        stage('Sonarqube Analysis #1'){
            environment {
                scannerHome = tool 'Sonar scanner'
            }
            steps {
                withSonarQubeEnv('Sonarserver') {
                    sh "${scannerHome}/bin/sonar-scanner"
                }
                if ("${json.projectStatus.status}" == "ERROR") {
                    currentBuild.result = 'FAILURE'
                     error('Pipeline aborted due to quality gate failure.')
                }
            }
          }
        stage('Build & SonarQube Analysis #2'){
            steps {
                agent {
                docker {image 'maven:3.8.6-openjdk-11-slim' }
                sh 'mvn sonar:sonar -Dsonar.projectKey=henrykrop2022_geolocation-23 -Dsonar.java.banaries=.'
                   }
                }   
            
            }
        
        }
    
    }
    
