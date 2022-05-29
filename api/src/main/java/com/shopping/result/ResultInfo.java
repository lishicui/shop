package com.shopping.result;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

/**
 * @author LSC
 * @date 2022/3/3
 */

@Data
@AllArgsConstructor
public class ResultInfo implements Serializable {
      private static final long serialVersionUID = - 7879597014511213651L;
      private Integer code;
      private String message;
      private Object data;

			/**
			 *
			 * 描述：产生一个成功带参数的结果
			 * @return
			 */
			public static ResultInfo genSuccessResultInfo(Object data,String message,Integer code) {
				return new ResultInfo(code,message,data);
			}
			/**
			 *
			 * 描述：产生一个成功带参数的结果
			 * @return
			 */
			public static ResultInfo genSuccessResultInfo(Object data,String message) {
				return new ResultInfo( 10000,message,data);
			}
			
				/**
			 *
			 * 描述：产生一个失败带参数的结果
			 * @return
			 */
			public static ResultInfo genErrorResultInfo(Object data,String message,Integer code) {
				return new ResultInfo(code,message,data);
			}


			public static ResultInfo genErrorResultInfo(String message,Integer code) {
				return new ResultInfo(code,message,null);
	}
}
