pipeline {
	agent any
	
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
