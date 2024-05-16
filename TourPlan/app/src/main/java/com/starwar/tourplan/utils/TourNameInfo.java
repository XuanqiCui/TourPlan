package com.starwar.tourplan.utils;

import android.os.Parcel;
import android.os.Parcelable;

public class TourNameInfo implements Parcelable {
    private String tourname;



    public TourNameInfo(){

    }

    protected TourNameInfo(Parcel in) {
        tourname = in.readString();
    }

    public String getTourname() {
        return tourname;
    }

    public void setTourname(String tourname) {
        this.tourname = tourname;
    }


    public static final Creator<TourNameInfo> CREATOR = new Creator<TourNameInfo>() {
        @Override
        public TourNameInfo createFromParcel(Parcel in) {
            return new TourNameInfo(in);
        }

        @Override
        public TourNameInfo[] newArray(int size) {
            return new TourNameInfo[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(tourname);
    }
}
