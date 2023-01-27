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
               def nexusRepo=readMavenPom.version.endsWith("SNAPSHOT") ? "Geo-SNAPSHOT":"geolocation-release"
            nexusArtifactUploader artifacts:
             [
                [
                artifactId: "${mavenPom.artifactId}", 
                classifier: '', 
                 file: "target/${mavenPom.artifactId}-${mavenPom.version}.${mavenPom.packaging}", 
                type: "${mavenPom.packaging}"
                ]
            ], 
            credentialsId: 'nexus2', 
            groupId: "${mavenPom.groupId}", 
            nexusUrl: '192.168.78.112:8081', 
            nexusVersion: 'nexus3', 
            protocol: 'http', 
            repository: nexusRepo, 
            version: "${mavenPom.version}"
                }
            }
       }
       
    }
}


    

