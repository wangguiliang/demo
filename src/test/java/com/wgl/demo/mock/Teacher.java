package com.wgl.demo.mock;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Description:
 *
 * @author: wangguiliang
 * @date:2019-09-22
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Teacher extends Person {

    private String destination;
}
