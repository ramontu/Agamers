package dam.agamers.gtidic.udl.agamers.views.recovery;

import androidx.lifecycle.MutableLiveData;

import dam.agamers.gtidic.udl.agamers.models.Account;
import dam.agamers.gtidic.udl.agamers.repositories.AccountRepo;

public class RecoverPasswordViewModel_2 {

    private AccountRepo accountRepo;
    private MutableLiveData<String> email;
    private MutableLiveData<String> recovery_code;
    private MutableLiveData<String> new_pass;
    private MutableLiveData<String> new_pass_2;

    public RecoverPasswordViewModel_2(){
        accountRepo = new AccountRepo();
        email = new MutableLiveData<>();
        recovery_code = new MutableLiveData<>();
        new_pass = new MutableLiveData<>();
        new_pass_2 = new MutableLiveData<>();
    }

    public void onRecover(){
        Account account = new Account();
        account.setEmail(email.getValue());
        account.setPassword(new_pass.getValue());
        account.setRecovery_code(recovery_code.getValue());
        accountRepo.recover2_newpass(account);
    }


}
