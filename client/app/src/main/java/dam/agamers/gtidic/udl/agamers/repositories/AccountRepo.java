package dam.agamers.gtidic.udl.agamers.repositories;

import androidx.lifecycle.MutableLiveData;

import dam.agamers.gtidic.udl.agamers.models.Account;
import dam.agamers.gtidic.udl.agamers.services.AccountServiceI;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Response;

public class AccountRepo{

    public void registerAccount(Account account){
        private AccountServiceI accountService;
        private MutableLiveData<String> mResponseRegister;

        public AccountRepo() {
            this.accountServiceI = new AccountServiceImpl();
            //this.accountDAO = new AccountDAOImpl();
            //this.mResponseLogin = new MutableLiveData<>();
            this.mResponseRegister = new MutableLiveData<>();

        }

        accountService.register(account).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                int return_code = response.code();  //200, 404, 401,...
                Log.d(TAG,"registerAccount() -> ha rebut el codi: " +  return_code);
                if (return_code == 200){
                    mResponseRegister.setValue("El registre s'ha fet correctament!!!!");
                }else{
                    String error_msg = "Error: " + response.errorBody();
                    mResponseRegister.setValue(error_msg);

                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {

                String error_msg = "Error: " + t.getMessage();

                mResponseRegister.setValue(error_msg);

            }

        });



    }

}
