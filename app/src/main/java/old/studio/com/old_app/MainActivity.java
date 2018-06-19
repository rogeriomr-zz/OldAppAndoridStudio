package old.studio.com.old_app;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;


public class MainActivity extends AppCompatActivity {

    private TextView cadastrar;
    private Button botao_logar;
    private EditText usuario, senha;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        cadastrar = findViewById(R.id.Idcadastrar);
        botao_logar = findViewById(R.id.btn_logar);
        usuario = findViewById(R.id.text_usuario);
        senha = findViewById(R.id.text_senha);

        cadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, Cadastro.class));
            }
        });

        botao_logar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new HttpReqTask().execute();
                //Toast.makeText(getApplicationContext(), "Email ou senha incorreto", Toast.LENGTH_LONG).show();
            }
        });
    }

    private class HttpReqTask extends AsyncTask<Void, Void, Login[]> {

        @Override
        protected Login[] doInBackground(Void... voids) {

            try {
                String url = "http://192.168.110.137:8080/login";
                Login login = new Login();
                login.setEmail(usuario.getText().toString());
                login.setSenha(senha.getText().toString());

                RestTemplate restTemplate = new RestTemplate();

                restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
                restTemplate.getMessageConverters().add(new StringHttpMessageConverter());

                String response = restTemplate.postForObject(url, login, String.class);

                startActivity(new Intent(MainActivity.this, Principal.class));
            } catch (Exception ex) {
                Log.e("MainActivity", ex.getMessage());
            }

            return null;
        }

    }

}
