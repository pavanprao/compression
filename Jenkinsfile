pipeline {
	agent any
	tools { 
        maven 'Maven 3.6.3' 
        jdk 'jdk8' 
    }
    stages {
	    stage('Checkout') {
            steps {
            	git "https://github.com/pavanprao/compression.git"
            }
    	}
	    stage('Compile') {
            steps {
            	sh 'mvn -Dmaven.test.failure.ignore=true install'
            }
            post {
                success {
                    junit 'target/surefire-reports/**/*.xml' 
                }
            }
    	}
	}
}