WORKING_DIRECTORY=$PWD
cd 
source .bash_profile
cd $WORKING_DIRECTORY/voice2text
mvn assembly:assembly -DdescriptorId=jar-with-dependencies
cd $WORKING_DIRECTORY/voice2text/target
GOOGLE_APPLICATION_CREDENTIALS=~/Downloads/speech2code-293021-9e5a1a5ad9b0.json java -jar voice2text-0.0.1-SNAPSHOT-jar-with-dependencies.jar