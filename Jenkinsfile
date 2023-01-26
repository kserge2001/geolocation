pipeline {
    agent any
     tools {
  maven 'M2_HOME'
     }

    stages{
        stage('Git Checkout'){
            steps{
                git branch: 'main', url: 'https://github.com/henrykrop2022/helloworld_jan_23.git'
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
                script{
                 sh 'mvn verify org.sonarsource.scanner.maven:sonar-maven-plugin:sonar -Dsonar.projectKey=henrykrop2022_geolocation-23'{
                    sh 'mvn clean package sonar:sonar'
                }
               }
            }
        }
    }
}