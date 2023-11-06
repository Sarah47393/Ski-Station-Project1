pipeline {
    agent any

   environment {
        registry = "gloria6056/jenkins-pipeline"
        registryCredential = 'dockerhub_id'
        dockerImage = ''
    }
    stages {
        stage ('GIT') {
            steps {
                echo "Getting project from Git"
                git branch: "Gloria", 
                    credentialsId: 'd2461957-c249-41f9-87e2-49dcec9e642b',
                    url: "https://github.com/Sarah47393/Ski-Station-Project1.git";
            }
        }

        stage("Maven Build") {
            steps {
                script {
                    echo "Building with Maven..."
                    sh "mvn package -DskipTests=true"
                }
            }
        }
       
        

        stage('JUnit/Mockito') {
            steps {
              script{
                    echo "Test unitaire..."
                    sh 'mvn test'
                }
            }
        }

        stage("Deploy to nexus") {
            steps {
                echo "Deploy to nexus..."
                sh "mvn deploy -DskipTests=true"
            }
        }

        stage("Build Docker image") {
            steps {
                script{
                    echo "Building Docker Image..."
                    dockerImage = docker.build registry + ":$BUILD_NUMBER"
                }
                
            }
        }

        stage('Deploy our image') {
            steps{
            script {
            echo "Push image to Docker Hub...."
            docker.withRegistry( '', registryCredential ) {
            dockerImage.push()
                       }
                 }
               }
        }

       


       
                            

       
       
    }
}
