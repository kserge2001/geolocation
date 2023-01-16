
pipeline {
    triggers {
  pollSCM('* * * * *')
    }
   agent any
    tools {
  maven 'M2_HOME'
}
environment {
    registry = '880385147960.dkr.ecr.us-east-1.amazonaws.com/jenkins-repository'
    registryCredential = 'jenkins-user'
    dockerimage = ''
}

    stages {

        stage("build & SonarQube analysis") {
             agent  any
        // docker { image 'maven:3.8.6-openjdk-11-slim' }
//    }
            
            
            steps {
              withSonarQubeEnv("SonarServer") {
                  sh 'mvn sonar:sonar -Dsonar.projectKey=henrykrop2022/geolocation-23 -Dsonar.java.binaries=.'
              }
            }
          }
        stage('Check Quality Gate') {
            steps {
                echo 'Checking quality gate...'
                script {
                    timeout(time: 20, unit: 'MINUTES') {
                        def qg = waitForQualityGate()
                        if (qg.status != 'OK') {
                            error "Pipeline stopped because of quality gate status: ${qg.status}"
                        }
                    }
                }
            }
        }
        
         
        stage('maven package') {
            steps {
                sh 'mvn clean'
                sh 'mvn install -DskipTests'
                sh 'mvn package -DskipTests'
            }
        }
        stage('Build Image') {
            
            steps {
                script{
                  def mavenPom = readMavenPom file: 'pom.xml'
                    dockerImage = docker.build registry + ":${mavenPom.version}"
                } 
            }
        }
        stage('Deploy image') {
           
            
            steps{
                script{ 
                    docker.withRegistry("https://"+registry,"ecr:us-east-1:"+registryCredential) {
                        dockerImage.push()
                    }
                }
            }
        }
        stage ('upload artifact'){
             steps{
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