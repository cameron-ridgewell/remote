package com.remote.utilities;

import android.content.Context;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

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
    private String command_ip = "http://192.168.0.106:8081/";//"http://10.0.3.2:8081/";
    private CommandRequestLibrary svc = new RestAdapter.Builder()
            .setEndpoint(command_ip).build()
            .create(CommandRequestLibrary.class);

    private static ExecutorService exec = Executors.newFixedThreadPool(1);

    private static ServerRequest instance = null;

    private Gson gson = new Gson();

    protected ServerRequest() {
    };

    public static ServerRequest getInstance() {
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

    public void setAVPowerOn(final ButtonAction ba) {
        Callable c = new Callable() {
            @Override
            public String call() {
                svc.setAVPowerOn(new Callback<String>() {
                    @Override
                    public void success(String resp, Response response) {
                        Log.v("Response", resp);
                        Log.v("Retrofit Success", "AV Power On Success");
                        ba.action(resp);
                    }

                    @Override
                    public void failure(RetrofitError error) {
                        Log.e("AV Power On Failed", "" + error.getMessage());
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

    public void setAVPowerOff(final ButtonAction ba) {
        Callable c = new Callable() {
            @Override
            public String call() {
                svc.setAVPowerOff(new Callback<String>() {
                    @Override
                    public void success(String resp, Response response) {
                        Log.v("Response", resp);
                        Log.v("Retrofit Success", "AV Power Off Success");
                        ba.action(resp);
                    }

                    @Override
                    public void failure(RetrofitError error) {
                        Log.e("AV Power Off Failed", "" + error.getMessage());
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

    public void getAVPower(final ButtonAction ba) {
        Callable c = new Callable() {
            @Override
            public String call() {
                svc.getAVPowerStatus(new Callback<String>() {
                    @Override
                    public void success(String resp, Response response) {
                        Log.v("Response", resp);
                        Log.v("Retrofit Success", "AV Power Status Success");
                        ba.action(resp);
                    }

                    @Override
                    public void failure(RetrofitError error) {
                        Log.e("AV Power Status Failed", "" + error.getMessage());
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

    public void getAVChannel(final ButtonAction ba) {
        Callable c = new Callable() {
            @Override
            public String call() {
                svc.getAVChannel(new Callback<String>() {
                    @Override
                    public void success(String resp, Response response) {
                        Log.v("Response", resp);
                        Log.v("Retrofit Success", "AV Channel Status Success");
                        ba.action(resp);
                    }

                    @Override
                    public void failure(RetrofitError error) {
                        Log.e("AV Channel Status Fail", "" + error.getMessage());
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

    public void setAVChannel(final String channel, final ButtonAction ba) {
        Callable c = new Callable() {
            @Override
            public String call() {
                svc.setAVChannel(channel, new Callback<String>() {
                    @Override
                    public void success(String resp, Response response) {
                        Log.v("Response", resp);
                        Log.v("Retrofit Success", "AV Channel Set Success");
                        ba.action(resp);
                    }

                    @Override
                    public void failure(RetrofitError error) {
                        Log.e("AV Channel Set Failure", "" + error.getMessage());
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

    public void increaseAVVolume(final ButtonAction ba) {
        Callable c = new Callable() {
            @Override
            public String call() {
                svc.increaseAVVolume(new Callback<String>() {
                    @Override
                    public void success(String resp, Response response) {
                        Log.v("Response", resp);
                        Log.v("Retrofit Success", "AV Volume Success");
                        ba.action(resp);
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

    public void decreaseAVVolume(final ButtonAction ba) {
        Callable c = new Callable() {
            @Override
            public String call() {
                svc.decreaseAVVolume(new Callback<String>() {
                    @Override
                    public void success(String resp, Response response) {
                        Log.v("Response", resp);
                        Log.v("Retrofit Success", "AV Volume Success");
                        ba.action(resp);
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

    public void setAVVolume(final double volume, final ButtonAction ba) {
        Callable c = new Callable() {
            @Override
            public String call() {
                svc.setAVVolume(volume, new Callback<String>() {
                    @Override
                    public void success(String resp, Response response) {
                        Log.v("Response", resp);
                        Log.v("Retrofit Success", "AV Volume Set Success");
                        ba.action(resp);
                    }

                    @Override
                    public void failure(RetrofitError error) {
                        Log.e("AV Volume Set Failed", "" + error.getMessage());
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

    public void setAVMute(final ButtonAction ba) {
        Callable c = new Callable() {
            @Override
            public String call() {
                svc.setAVMute(new Callback<String>() {
                    @Override
                    public void success(String resp, Response response) {
                        Log.v("Response", resp);
                        Log.v("Retrofit Success", "AV Volume Mute Success");
                        ba.action(resp);
                    }

                    @Override
                    public void failure(RetrofitError error) {
                        Log.e("AV Volume Mute Failed", "" + error.getMessage());
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

    public void getAVVolume(final ButtonAction ba) {
        Callable c = new Callable() {
            @Override
            public String call() {
                svc.getAVVolume(new Callback<String>() {
                    @Override
                    public void success(String resp, Response response) {
                        Log.v("Response", resp);
                        Log.v("Retrofit Success", "AV Volume Status Success");
                        ba.action(resp);
                    }

                    @Override
                    public void failure(RetrofitError error) {
                        Log.e("AV Volume Status Failed", "" + error.getMessage());
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
