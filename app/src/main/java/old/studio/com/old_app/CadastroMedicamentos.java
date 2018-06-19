package old.studio.com.old_app;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class CadastroMedicamentos extends AppCompatActivity {

    private EditText nome_med, dosagem_med, horario_med, tempo_med;
    private Button cadastrar_med;
    public static MedicamentosClass medicamentosClass = new MedicamentosClass();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_medicamentos);

        nome_med = findViewById(R.id.NomemedId);
        dosagem_med = findViewById(R.id.DosagemmedId);
        horario_med = findViewById(R.id.HorariomedId);
        tempo_med = findViewById(R.id.TempomedId);
        cadastrar_med = findViewById(R.id.cadastrarmedId);

        cadastrar_med.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                medicamentosClass.setNome_medicamento(nome_med.getText().toString());

            }
        });

    }
    }