package jace.shim.tdd.chapter7;

public class UserRegister {
    private final UserRepository userRepository;
    WeakPasswordChecker weakPasswordChecker;
    EmailNotifier emailNotifier;
    public UserRegister(WeakPasswordChecker weakPasswordChecker, MemoryUserRepository userRepository, EmailNotifier emailNotifier) {
        this.weakPasswordChecker = weakPasswordChecker;
        this.userRepository = userRepository;
        this.emailNotifier = emailNotifier;
    }


    public void register(String id, String pw, String email) {
        if (weakPasswordChecker.checkPasswordWeak(pw)) {
            throw new WeakPasswordException();
        }
        final User user = userRepository.findById(id);
        if (user != null) {
            throw new DuplicateIdException();
        }
        this.userRepository.save(new User(id, pw, email));
        emailNotifier.sendRegisterEmail(email);
    }
}
