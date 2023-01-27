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
        stage(' SonarQube Analysis'){
            steps {
                withSonarQubeEnv('SonarQube Scanner') {
                sh 'mvn sonar:sonar'
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
       stage('Upload to Nexus'){
            steps{
                script{
                    def mavenPom = readMavenPom file: 'pom.xml'
                    nexusArtifactUploader artifacts:
                     [
                        [
                        artifactId: 'bioMedical',
                        classifier: '', 
                         file: "target/${mavenPom.artifactId}-${mavenPom.version}.${mavenPom.packaging}",
                        type: 'jar'
                        ]
                    ], 
                    credentialsId: 'Nexus-repo',
                     groupId: 'com.spring', 
                     nexusUrl: '192.168.78.88:9000', 
                     nexusVersion: 'nexus3', 
                     protocol: 'http', 
                     repository: 'geolocation-release', 
                     version: '0.0.6-RELEASE'
                }
            }
       }
       
    }
}


    

