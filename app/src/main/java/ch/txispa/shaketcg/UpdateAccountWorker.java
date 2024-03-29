package ch.txispa.shaketcg;

import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.work.Worker;
import androidx.work.WorkerParameters;

import ch.txispa.shaketcg.database.AppDatabase;
import ch.txispa.shaketcg.database.entity.User;

public class UpdateAccountWorker extends Worker {

    public UpdateAccountWorker(@NonNull Context context, @NonNull WorkerParameters workerParams) {
        super(context, workerParams);
    }

    @NonNull
    @Override
    public Result doWork() {
        Log.e("Worker", "I'M DOING WORK");

        try {
            int userId = 1; // Assuming user ID is known
            User user = AppDatabase.getInstance(getApplicationContext()).userDao().findByUsername("user");
            int userAccount = user.getMoney();
            AppDatabase.getInstance(getApplicationContext()).userDao().updateMoney(userId, userAccount + 100);
            return Result.success();
        } catch (Exception e) {
            return Result.failure();
        }
    }
}


