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
                sh 'mvn clean install package'
            }
        }
        stage('Quality-Gate'){
            steps {
                script{
                    timeout(time: 1, unit: 'HOURS') {
                         waitForQualityGate abortPipeline: true
                       }
                    }
            }   
        }
        stage('SonarQube Analysis'){
            
            steps {
                script {
                    withSonarQubeEnv('My SonarQube Server') {  
                        withMaven(maven:'Maven 3.5') {
                         sh 'mvn clean package sonar:sonar'
                        
                    }
                }  
            }
        }
        
    }
}