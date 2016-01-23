package com.remote.utilities;

import android.content.Context;
import android.util.Base64;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import java.util.Objects;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by cameronridgewell on 7/30/15.
 */
public class ServerRequest{
    private String command_ip = "http://98.249.35.81:8081/";
    private CommandRequestLibrary svc = new RestAdapter.Builder()
            .setEndpoint(command_ip).build()
            .create(CommandRequestLibrary.class);

    private static ExecutorService exec = Executors.newFixedThreadPool(1);

    private static ServerRequest instance = null;

    private Gson gson = new Gson();

    protected ServerRequest(){};

    public static ServerRequest getInstance(){
        if (instance == null) {
            ServerRequest newInstance = new ServerRequest();
            return newInstance;
        } else {
            return instance;
        }
    }

    public void loadUser(Context context) {
        User user = AppData.getInstance().getThisUser(context.getApplicationContext());
    }

    public void toggleTVPower() {
        Callable c = new Callable() {
            @Override
            public String call() {
                svc.toggleTVPower(new Callback<JsonObject>() {
                    @Override
                    public void success(JsonObject resp, Response response) {
                        Log.v("Retrofit Success", "TV Power Success");
                    }

                    @Override
                    public void failure(RetrofitError error) {
                        if (error.getMessage() != null) {
                            Log.e("TV Power Failed", "" + error.getMessage());
                        } else {
                            Log.e("TV Power Failed", "Null error");
                        }
                    }
                });
                return "";
            }
        };
        try {
            exec.submit(c).get();
        } catch (ExecutionException e) {
            Log.e("Interrupted Exception", "" + e.getMessage());
        } catch (InterruptedException e) {
            Log.e("Execution Exception", "" + e.getMessage());
        }
    }

    public void inputTVNumber(final int number) {
        Callable c = new Callable() {
            @Override
            public String call() {
                svc.inputTVNumber(number, new Callback<JsonObject>() {
                    @Override
                    public void success(JsonObject resp, Response response) {
                        Log.v("Retrofit Success", "TV Number Success");
                    }

                    @Override
                    public void failure(RetrofitError error) {
                        Log.e("TV Number Failed", "" + "" + error.getMessage());
                    }
                });
                return "";
            }
        };
        try {
            exec.submit(c).get();
        } catch (ExecutionException e) {
            Log.e("Interrupted Exception", "" + e.getMessage());
        } catch (InterruptedException e) {
            Log.e("Execution Exception", "" + e.getMessage());
        }
    }

    public void toggleAVPower() {
        Callable c = new Callable() {
            @Override
            public String call() {
                svc.toggleAVPower(new Callback<JsonObject>() {
                    @Override
                    public void success(JsonObject resp, Response response) {
                        Log.v("Retrofit Success", "AV Power Success");
                    }

                    @Override
                    public void failure(RetrofitError error) {
                        Log.e("AV Power Failed", "" + error.getMessage());
                    }
                });
                return "";
            }
        };
        try {
            exec.submit(c).get();
        } catch (ExecutionException e) {
            Log.e("Interrupted Exception", "" + e.getMessage());
        } catch (InterruptedException e) {
            Log.e("Execution Exception", "" + e.getMessage());
        }
    }

    public void inputAVNumber(final int number) {
        Callable c = new Callable() {
            @Override
            public String call() {
                svc.inputAVNumber(number, new Callback<JsonObject>() {
                    @Override
                    public void success(JsonObject resp, Response response) {
                        Log.v("Retrofit Success", "AV Number Success");
                    }

                    @Override
                    public void failure(RetrofitError error) {
                        Log.e("AV Number Failed", "" + error.getMessage());
                    }
                });
                return "";
            }
        };
        try {
            exec.submit(c).get();
        } catch (ExecutionException e) {
            Log.e("Interrupted Exception", "" + e.getMessage());
        } catch (InterruptedException e) {
            Log.e("Execution Exception", "" + e.getMessage());
        }
    }

    public void increaseAVVolume() {
        Callable c = new Callable() {
            @Override
            public String call() {
                svc.increaseAVVolume(new Callback<JsonObject>() {
                    @Override
                    public void success(JsonObject resp, Response response) {
                        Log.v("Retrofit Success", "AV Volume Success");
                    }

                    @Override
                    public void failure(RetrofitError error) {
                        Log.e("AV Volume Failed", "" + error.getMessage());
                    }
                });
                return "";
            }
        };
        try {
            exec.submit(c).get();
        } catch (ExecutionException e) {
            Log.e("Interrupted Exception", "" + e.getMessage());
        } catch (InterruptedException e) {
            Log.e("Execution Exception", "" + e.getMessage());
        }
    }

    public void decreaseAVVolume() {
        Callable c = new Callable() {
            @Override
            public String call() {
                svc.decreaseAVVolume(new Callback<JsonObject>() {
                    @Override
                    public void success(JsonObject resp, Response response) {
                        Log.v("Retrofit Success", "AV Volume Success");
                    }

                    @Override
                    public void failure(RetrofitError error) {
                        Log.e("AV Volume Failed", "" + error.getMessage());
                    }
                });
                return "";
            }
        };
        try {
            exec.submit(c).get();
        } catch (ExecutionException e) {
            Log.e("Interrupted Exception", "" + e.getMessage());
        } catch (InterruptedException e) {
            Log.e("Execution Exception", "" + e.getMessage());
        }
    }

    public void changeHDMI(final String port) {
        Callable c = new Callable() {
            @Override
            public String call() {
                svc.changeHDMI(port, new Callback<JsonObject>() {
                    @Override
                    public void success(JsonObject resp, Response response) {
                        Log.v("Retrofit Success", "AV HDMI port Success");
                    }

                    @Override
                    public void failure(RetrofitError error) {
                        Log.e("AV HDMI port Failed", "" + "" + error.getMessage());
                    }
                });
                return "";
            }
        };
        try {
            exec.submit(c).get();
        } catch (ExecutionException e) {
            Log.e("Interrupted Exception", "" + e.getMessage());
        } catch (InterruptedException e) {
            Log.e("Execution Exception", "" + e.getMessage());
        }
    }

    public void toggleCMPower() {
        Callable c = new Callable() {
            @Override
            public String call() {
                svc.toggleCMPower(new Callback<JsonObject>() {
                    @Override
                    public void success(JsonObject resp, Response response) {
                        Log.v("Retrofit Success", "Comcast Power Success");
                    }

                    @Override
                    public void failure(RetrofitError error) {
                        Log.e("Comcast Power Failed", "" + error.getMessage());
                    }
                });
                return "";
            }
        };
        try {
            exec.submit(c).get();
        } catch (ExecutionException e) {
            Log.e("Interrupted Exception", "" + e.getMessage());
        } catch (InterruptedException e) {
            Log.e("Execution Exception", "" + e.getMessage());
        }
    }

    public void inputCMNumber(final int number) {
        Callable c = new Callable() {
            @Override
            public String call() {
                svc.inputCMNumber(number, new Callback<JsonObject>() {
                    @Override
                    public void success(JsonObject resp, Response response) {
                        Log.v("Retrofit Success", "CM Number Success");
                    }

                    @Override
                    public void failure(RetrofitError error) {
                        Log.e("CM Number Failed", "" + error.getMessage());
                    }
                });
                return "";
            }
        };
        try {
            exec.submit(c).get();
        } catch (ExecutionException e) {
            Log.e("Interrupted Exception", "" + e.getMessage());
        } catch (InterruptedException e) {
            Log.e("Execution Exception", "" + e.getMessage());
        }
    }

    public String login() {
        Callable c = new Callable() {
            @Override
            public String call() {
                svc.login(new Callback<JsonObject>() {
                    @Override
                    public void success(JsonObject resp, Response response) {
                        Log.v("Retrofit Success", "Login Success");
                    }

                    @Override
                    public void failure(RetrofitError error) {
                        Log.e("Login Failed", "" + error.getMessage());
                    }
                });
                return "";
            }
        };
        try {
            exec.submit(c).get();
        } catch (ExecutionException e) {
            Log.e("Interrupted Exception", "" + e.getMessage());
        } catch (InterruptedException e) {
            Log.e("Execution Exception", "" + e.getMessage());
        }
        return "";
    }
}
