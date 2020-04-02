pipeline {
	agent any
	
    stages {
	    stage('Checkout') {
            steps {
            	git "https://git.enstage-sas.com/eguard/compression.git"
            }
    	}
	    stage('Compile') {
            steps {
            	sh "./mvnw clean install -DskipTests"
            }
    	}
	}
}