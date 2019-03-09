package lesson19.hw;

import java.util.Arrays;

public class Controller {

    public boolean put(Storage storage, File file) {
        if (storage == null || file == null)
            return false;

        try {
            if (!formatIsCorrect(storage, file))
                throw new Exception("File with id " + file.getId() + " has not been added in storage with id " + storage.getId() + ", because this storage supported formats as " + Arrays.toString(storage.getFormatsSupported()) + ". This file have format " + file.getFormat());

            if (fileInThere(storage, file))
                throw new Exception("File with id " + file.getId() + " already exists in in storage with id " + storage.getId());

            if (storage.getFilledSize() + file.getSize() > storage.getStorageSize())
                throw new Exception("File with id " + file.getId() + " has not been added in storage with id " + storage.getId() + ", because size in storage not enough. Last size is " + (storage.getStorageSize() - storage.getFilledSize()) + ". New file size is " + file.getSize());

            //add file
            File[] files = storage.getFiles();
            if (files == null) {
                files = new File[]{file};
            } else {
                files = Arrays.copyOf(files, files.length + 1);
                files[files.length - 1] = file;
            }
            storage.setFiles(files);
            System.out.println("File with id " + file.getId() + " add successfully in storage with id " + storage.getId());
            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }

    }


    public void delete(Storage storage, File file) {
        if (storage == null || file == null)
            return;

        try {
            if (!fileInThere(storage, file))
                throw new Exception("File with id " + file.getId() + " and name " + file.getName() + " has not been deleted in storage with id " + storage.getId() + ", because it is non there");


            //delete file
            for (int i = 0; i < storage.getFiles().length; i++) {
                if (storage.getFiles()[i].equals(file))
                    storage.getFiles()[i] = null;
            }
            File[] files = new File[storage.getFiles().length - 1];
            for (int i = 0, j = 0; i < storage.getFiles().length; i++) {
                if (storage.getFiles()[i] == null)
                    continue;
                files[j] = storage.getFiles()[i];
                j++;
            }
            storage.setFiles(files);
            System.out.println("File with id " + file.getId() + " delete successfully in storage with id " + storage.getId());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }


    public void transferAll(Storage storageFrom, Storage storageTo) {
        if (storageFrom == null || storageTo == null) {
            System.out.println("Storage From or Storage To does not exist");
            return;
        }

        for (File file : storageFrom.getFiles()) {
            transferFile(storageFrom, storageTo, file.getId());
        }

    }

    public void transferFile(Storage storageFrom, Storage storageTo, long id) {
        if (storageFrom == null || storageTo == null) {
            System.out.println("Storage From or Storage To does not exist");
            return;
        }
        File file = findFileById(storageFrom, id);
        try {
            if (file == null)
                throw new Exception("File with id " + id + " not found in storage with id " + storageFrom.getId());
            if (!fileInThere(storageFrom, file))
                throw new Exception("File with id " + file.getId() + " and name " + file.getName() + " has not been deleted in storage with id " + storageFrom.getId() + ", because it is non there");

            if(this.put(storageTo, file))
                this.delete(storageFrom, file);

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }


    private boolean fileInThere(Storage storage, File file) {
        boolean validation = false;
        if (storage.getFiles() != null) {
            for (File file1 : storage.getFiles()) {
                if (file1.equals(file))
                    validation = true;
            }
        }
        return validation;
    }

    private boolean formatIsCorrect(Storage storage, File file) {
        boolean validation = false;
        for (String format : storage.getFormatsSupported()) {
            if (file.getFormat() == format)
                validation = true;
        }
        return validation;
    }

    private File findFileById(Storage storage, long id) {
        File file = null;
        if (storage.getFiles() != null) {
            int i = 0;
            for (File file1 : storage.getFiles()) {
                if (file1.getId() == id)
                    file = storage.getFiles()[i];
                i++;
            }
        }
        return file;
    }
}
