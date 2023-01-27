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
        }
        stage('UNIT Testing'){
            steps{
               sh 'mvn test'
            }
        }
    }
    
//         stage('Integration Testing'){
//             steps{
//                 script {
//                sh 'mvn verify -DskipUnitTests'  
//                 }
//             }
//         }
//         stage('Maven Build'){
//             steps {
//                 sh 'mvn clean install package'
//             }
//         }
//         stage("build & SonarQube analysis") {
//             agent {
//         docker { image 'maven:3.8.6-openjdk-11-slim' }
//                 }
//              }
//             steps {
//               withSonarQubeEnv('SonarServer') {
//                   sh 'mvn verify org.sonarsource.scanner.maven:sonar-maven-plugin:sonar -Dsonar.projectKey=henrykrop2022_geolocation-23'
//               }
//         }
//         stage('Quality-Gate'){
//             steps {
//                 script{
//                     timeout(time: 1, unit: 'HOURS') {
//                          waitForQualityGate false
                    
//                 }
//             }   
//         }   
//     }
// }