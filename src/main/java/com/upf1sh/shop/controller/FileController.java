package com.upf1sh.shop.controller;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.ArrayUtil;
import cn.hutool.crypto.digest.DigestUtil;
import com.upf1sh.shop.utils.Result;
import javafx.beans.binding.MapExpression;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Hashtable;
import java.util.Map;

@RestController
@RequestMapping("/file")
@CrossOrigin
public class FileController {
    @PostMapping("/upload")
    public void reciveFileSeg(MultipartFile file,
                              String segcode,
                              String allcode,
                              Integer index,
                              Integer allindex) throws IOException {

        System.out.println(file + allcode);
    }

}
