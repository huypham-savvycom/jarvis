package com.jarvis.android.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * UnixTime model to convert String time to Long
 *
 */
public class UnixTime implements Parcelable {

	private long time;

	public UnixTime() {
		super();
	}

	public UnixTime(long time) {
		super();
		this.time = time;
	}

	public long getTime() {
		return time;
	}

	public void setTime(long time) {
		this.time = time;
	}
	
    protected UnixTime(Parcel in) {
        time = in.readLong();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(time);
    }

    public static final Creator<UnixTime> CREATOR = new Creator<UnixTime>() {
        @Override
        public UnixTime createFromParcel(Parcel in) {
            return new UnixTime(in);
        }

        @Override
        public UnixTime[] newArray(int size) {
            return new UnixTime[size];
        }
    };
}