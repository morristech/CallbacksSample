package com.romellfudi.callbacks;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.text.ParseException;

public class MainActivity extends AppCompatActivity {
    TextView txt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(freddy.fudi.callbacks.R.layout.activity_main);
        txt = (TextView) findViewById(freddy.fudi.callbacks.R.id.txt);
    }

    public void Callback(View view) {
        myController.getInstance().guardarData(txt.getText().toString(),
                new Callback() {
                    @Override
                    public void successed(Object... objects) {
                        show((String) objects[0]);
                        txt.append(objects[1].toString());
                    }

                    @Override
                    public void failed(Object... objects) {
                        show((String) objects[0]);

                    }
                });
    }

    public void EnviarCallback(View view) {

        //sendCallback
        myController.getInstance().enviarData(2, new float[]{0, 8}, new EnviarCallback() {
            @Override
            public void errorAlParsear(Exception exception) {
                if (exception != null)
                    show(exception.getMessage());

            }

            @Override
            public void successed(Object... objects) {
                show(objects[0].toString());

            }

            @Override
            public void failed(Object... objects) {
                show(objects[0].toString());
            }
        });
    }

    public void RestCallBack(View view) {
        myController.getInstance().enviarData(0, new float[]{})
                .enqueRest(new RestCallBack() {
                    @Override
                    public void httpOK(Object... objects) {
                        int num = (int) objects[0];
                        float[] floats = (float[]) objects[1];
                        Model model = (Model) objects[2];

                    }

                    @Override
                    public void errorHttp(Exception exception, Object... objects) {
                        if (exception == null)
                            show(exception.getMessage());
                    }

                    @Override
                    public void successed(Object... objects) {
                        show(objects[0].toString());
                    }

                    @Override
                    public void failed(Object... objects) {
                        show(objects[0].toString());
                    }
                });

    }

    public void MathCallback(View view) {
        //MathCallback
        myController.getInstance().resolverProblema(3, 4, new MathCallback() {
            @Override
            void Mathsuccessed(Object... objects) {
                show(objects[0].toString());
            }
        });
    }

    public void ObjectCallback(View view) {
//        ObjectCallback
        myController.getInstance().resolverObject(null, new ObjectCallback() {
            @Override
            public void successed(Object... objects) {
                show(objects[0].toString());
            }
        });
    }

    public void AbstractCallback(View view) {
//        AbstractCallback
        myController.getInstance().resolverAbstract(3, "34", new AbstractCallback() {
            @Override
            public void successed(Object... objects) {
                show(objects[0].toString());
            }

            @Override
            public void failed(Exception exception, Object... objects) {
                if (exception!=null)
                    show(exception.getMessage().toString());
            }
        });

    }

    public void MethodParse(View view){
        myController.getInstance().getParsedObject("", new myController.MethodCall(){
            @Override
            public void onFail(Object... objects) {

            }

            @Override
            public void onParse(ParseException exception, Object... objects) {

            }

            @Override
            public void onSuccess(Object... objects) {

            }
        });
    }

    public void OtherMethodParse(View view){
        myController.getInstance().getHttp("", new myController.HttpCall<Model>() {
            @Override
            public void errorHTTP(String codeError, Exception exception, Object... objects) {

            }

            @Override
            public void onResponse(Model model, Object... objects) {

            }
        });
    }

    public void twoSteps(View view){
        myController.getInstance().twoSteps(new PreCallback() {
            @Override
            public void successed(Object... objects) {
                show(objects[0].toString());
            }

            @Override
            public void failed(Object... objects) {
                show(objects[0].toString());
            }
        }, new PosCallback() {
            @Override
            public void successed(Object... objects) {
                show(objects[0].toString());
            }

            @Override
            public void failed(Object... objects) {
                show(objects[0].toString());
            }
        });
    }

    public void interfaces(View view){
        new TemplateController<Model,Model2>()
                .methodStatickCall(new TemplateController.TemplateCallback<Model, Model2>() {
                    @Override
                    public Model2 methodPoorReturnY(Model model) {
                        return new Model2();
                    }

                    @Override
                    public Model methodSuperReturnA(Model2 model2) {
                        return new Model();
                    }
                });
    }

    private void show(String string) {
        Toast.makeText(MainActivity.this, string, Toast.LENGTH_SHORT).show();
    }
}
