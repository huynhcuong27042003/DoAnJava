package org.example.dajava.Service;

import org.example.dajava.Model.ChucVu;
import org.example.dajava.Repository.ChucVuRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ChucVuService {
    /*@Autowired
    private final ChucVuRepository chucVuRepository;

    public List<ChucVu> getAllChucVu() {
        return chucVuRepository.findAll();
    }

    public Optional<ChucVu> getChucVuById(Integer id) {
        return chucVuRepository.findById(id);
    }

    public ChucVu addChucVu(ChucVu chucVu) {
        return chucVuRepository.save(chucVu);
    }

    public ChucVu editChucVu(ChucVu chucVu) {
        if (chucVu.getIDChucVu() == null || !chucVuRepository.existsById(chucVu.getIDChucVu())) {
            throw new IllegalArgumentException("Chức vụ không tồn tại");
        }
        return chucVuRepository.save(chucVu);
    }

    public void deleteChucVu(Integer id) {
        chucVuRepository.deleteById(id);
    }
*/
}
