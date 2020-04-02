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
            	sh "./mvnw clean install -DskipTests"
            }
    	}
	}
}