pipeline {
    agent any
    tools {
        maven 'M2_HOME'
    }
    stages {
        stage("maven build") {
            steps{
                sh 'mvn clean install package'
            }

        }
        stage("upload artifact") {
            steps{
                script{
                    def mavenPom = readMavenPom file: 'pom.xml'
                    nexusArtifactUploader artifacts: 
                    [[artifactId:  "${mavenPom.artifactid}", 
                    classifier: '', 
                    file: "target/${mavenPom.artifactid}-${mavenPom.version}.${mavenPom.packaging}", 
                    type: "${mavenPom.packaging}"]], 
                    credentialsId: 'Nexusid', 
                    groupId:  "${mavenPom.groupid}", 
                     nexusUrl: '69.164.206.106:8081', 
                      nexusVersion: 'nexus3', 
                       protocol: 'http', 
                        repository: 'Biomedical', 
                         version: "${mavenPom.version}"
                }          
            }

        }
        
    }
}