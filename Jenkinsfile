pipeline{
    agent any
    tools{
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
        stage(' SonarQube Analysis'){
            steps {
                withSonarQubeEnv('SonarQube') {
                sh 'mvn clean verify sonar:sonar'
                }
            }   
            
        }
        stage('Quality Gate'){
            steps{
                script {
                    waitForQualityGate abortPipeline: false, credentialsId: 'sonarqube-token'
                }
            }
       }
       stage('Docker Image Build'){
         steps{
            script{
                sh 'docker image build -t $JOB_NAME:V1$BUILD_ID .'
                sh ' docker image tag $JOB_NAME:V1$BUILD_ID henryrop/$JOB_NAME:V1$BUILD_ID'
                sh ' docker image tag $JOB_NAME:V1$BUILD_ID henryrop/$JOB_NAME:latest'
                }
            }
        }
        
        stage('Push Image to Dockerhub') {
             steps{
                script{
                    withCredentials([string(credentialsId: 'henryrop', variable: 'dockerhub_cred')]) {
                        sh 'docker login -u henryrop -p ${dockerhub_cred}'
                        sh 'docker image push henryrop/$JOB_NAME:V1$BUILD_ID'
                        sh  'docker image push henryrop/$JOB_NAME:latest'
                   }
                }
             }
        }

    }
}

       
            
//         stage('Upload to Nexus'){
//             steps{
//                 script{
//                def mavenPom = readMavenPom file: 'pom.xml'
//             nexusArtifactUploader artifacts:
//              [
//                 [
//                 artifactId: "${mavenPom.artifactId}", 
//                 classifier: '', 
//                  file: "target/${mavenPom.artifactId}-${mavenPom.version}.${mavenPom.packaging}", 
//                 type: "${mavenPom.packaging}"
//                 ]
//             ], 
//             credentialsId: 'nexus2', 
//             groupId: "${mavenPom.groupId}", 
//             nexusUrl: '192.168.78.112:8081', 
//             nexusVersion: 'nexus3', 
//             protocol: 'http', 
//             repository: 'geolocation-release',
//             version: "${mavenPom.version}"
//                 }
//             }
       
//         }
//     }
// }

