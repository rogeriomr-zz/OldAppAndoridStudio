package old.studio.com.old_app;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

public class cadastro_Especifico extends AppCompatActivity {

    private Button btncadastrar;
    private EditText name, date, telefone, email, senha, sex, cm, nc, gs, def, fm, ba, af;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro__especifico);

        btncadastrar = findViewById(R.id.Id_btn_cadastrar);
        name = findViewById(R.id.text_nome);
        date = findViewById(R.id.text_nascimento);
        telefone = findViewById(R.id.text_telefone);
        email = findViewById(R.id.text_email);
        senha = findViewById(R.id.text_senha);
        sex = findViewById(R.id.text_sexo);
        cm = findViewById(R.id.text_convenio);
        nc = findViewById(R.id.text_numcar);
        gs = findViewById(R.id.text_gruposang);

        btncadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new HttpReqTask().execute();
            }
        });
    }

    private class HttpReqTask extends AsyncTask<Void, Void, Usuario[]> {

        @Override
        protected Usuario[] doInBackground(Void... voids) {

            try {
                String url = "http://192.168.0.106:8080/usuarios";
                Usuario usuario = new Usuario();
                usuario.setNome(name.getText().toString());
                usuario.setEmail(email.getText().toString());
                usuario.setNascimento(date.getText().toString());
                usuario.setSexo(sex.getText().toString());
                usuario.setSenha(senha.getText().toString());
                usuario.setTelefone(telefone.getText().toString());
                usuario.setConveniomedico(cm.getText().toString());
                usuario.setNumerocarteirinha(nc.getText().toString());
                usuario.setGruposang(gs.getText().toString());
                usuario.setDeficiencia("sim");
                usuario.setFuma("não");
                usuario.setAlcoolico("sim");
                usuario.setAtvfisica("não");

                RestTemplate restTemplate = new RestTemplate();

                restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
                restTemplate.getMessageConverters().add(new StringHttpMessageConverter());

                String response = restTemplate.postForObject(url, usuario, String.class);

                startActivity(new Intent(cadastro_Especifico.this, MainActivity.class));
            } catch (Exception ex) {
                Log.e("cadastro_Especifico", ex.getMessage());
            }

            return null;
        }

    }
}
