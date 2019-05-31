package com.example.huynhvinh.applazada_java.model.Room.repository;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;

import com.example.huynhvinh.applazada_java.model.Room.dao.HandleSanPhamDAO;
import com.example.huynhvinh.applazada_java.model.Room.database.SanPham_DatabaseRoom;
import com.example.huynhvinh.applazada_java.model.Room.object.SanPham_Room;

import java.util.List;

public class SanPhamRepository {

    private HandleSanPhamDAO handleSanPhamDAO;
    private LiveData<List<SanPham_Room>> allProucts;

    public SanPhamRepository(Application application) {
        SanPham_DatabaseRoom sanPham_databaseRoom =SanPham_DatabaseRoom.getInstance(application);
        handleSanPhamDAO = sanPham_databaseRoom.handleSanPhamDAO();
        allProucts =handleSanPhamDAO.getAllProducts();
    }

    public void themSanPham(SanPham_Room sanPham_room){
            new InsertProductAsyncTask(handleSanPhamDAO).execute(sanPham_room);
    }


    public LiveData<List<SanPham_Room>> layDanhSachSanPham(){
        return allProucts;
    }

    public void deleteAllProduct(){
        new DeleteAllNoteAsyncTask(handleSanPhamDAO).execute();
    }



    private  static class InsertProductAsyncTask extends AsyncTask<SanPham_Room, Void, Void> {

        private  HandleSanPhamDAO  handleSanPhamDAO;


        public InsertProductAsyncTask(HandleSanPhamDAO handleSanPhamDAO) {
            this.handleSanPhamDAO = handleSanPhamDAO;
        }


        @Override
        protected Void doInBackground(SanPham_Room... sanPham_rooms) {
            handleSanPhamDAO.themSanPham(sanPham_rooms[0]);
            return null;
        }
    }

    private  static class getLLProductAsyncTask extends AsyncTask<SanPham_Room, Void, Void> {

        private  HandleSanPhamDAO  handleSanPhamDAO;


        public getLLProductAsyncTask(HandleSanPhamDAO handleSanPhamDAO) {
            this.handleSanPhamDAO = handleSanPhamDAO;
        }


        @Override
        protected Void doInBackground(SanPham_Room... sanPham_rooms) {
            handleSanPhamDAO.themSanPham(sanPham_rooms[0]);
            return null;
        }
    }

    private  static class DeleteAllNoteAsyncTask extends AsyncTask<SanPham_Room, Void, Void> {

        private  HandleSanPhamDAO  handleSanPhamDAO;


        public DeleteAllNoteAsyncTask(HandleSanPhamDAO handleSanPhamDAO) {
            this.handleSanPhamDAO = handleSanPhamDAO;
        }


        @Override
        protected Void doInBackground(SanPham_Room... sanPham_rooms) {
            handleSanPhamDAO.deleteAllNotes();
            return null;
        }
    }

}
