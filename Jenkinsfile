pipeline{
    agent any
    tools {
        maven 'M2_HOME'
    }
    stages{
        stage('mavin build'){
            steps{
                sh 'mvn clean install package'
            }

        }
        stage('upload artifact'){
            steps{
                script{
                    def mavenPom = readMavenPom file: 'pom.xml'

                    nexusArtifactUploader artifacts: 
                    [[artifactId: "${mavenPom.artifactId}",
                    classifier: '',
                    file: "target/${mavenPom.artifactId}-${mavenPom.version}.${mavenPom.packaging}",
                    type: "${mavenPom.packaging}"]],
                    credentialsId: 'NexusID',
                    groupId: "${mavenPom.groupId}",
                     nexusUrl: '45.33.7.113:8081',
                      nexusVersion: 'nexus3',
                       protocol: 'http',
                        repository: 'biom',
                         version: "${mavenPom.version}" 
                }
            }

        }
        stage('list the dir'){
            steps{
                sh 'ls'
            }

        }
    }
}