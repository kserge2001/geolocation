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
                   withSonarQubeEnv(credentialsId: 'SONAR_TOKEN') {
                        sh 'mvn clean package sonar:sonar'
                    }
                }  
            }
        }
        stage ('Quality Gate Status'){

            steps {

                script {
                    
                    waitForQualityGate abortPipeline: false, credentialsId: 'SONAR_TOKEN' {
                }
            }   
        }
        stage ('upload artifact') {
            steps {
                script {
                    def mavenPom = readMavenPom file: 'pom.xml'
            nexusArtifactUploader artifacts:
             [[artifactId: "${mavenPom.artifactId}", 
                classifier: '', 
                  file: "target/${mavenPom.artifactId}-${mavenPom.version}.${mavenPom.packaging}", 
                    type: "${mavenPom.packaging}"]], 
                       credentialsId: "NexusID", 
                          groupId: "${mavenPom.groupId}", 
                            nexusUrl: '192.168.78.112:8081', 
                              nexusVersion: 'nexus3', 
                                protocol: 'http', 
                                  repository: 'maven-nexus-repo',
                                    version: "${mavenPom.version}"
                        }
                    }   
                } 
            }
    }
}