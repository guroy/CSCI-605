package n1;

public interface MyInterface extends java.rmi.Remote {
	String askInput();
	int askIntToGuess(); // the mystery number we need to guess must be given to the server
	int checkInput(int number);
}
