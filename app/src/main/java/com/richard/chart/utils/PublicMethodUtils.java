package com.richard.chart.utils;

import android.text.TextUtils;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @project：sinopay
 * @author：- richard on 2020/7/17 0017 11:31
 * @email：583206137@qq.com
 */
public class PublicMethodUtils {

    /**
     * 获取格式后的日期时间
     *
     * @param dateTime
     * @return
     */
    public static String getFormatMonthDateTime(String dateTime) {
        if(TextUtils.isEmpty(dateTime) || dateTime.contains("-")) return dateTime;
        String dateNowStr = "";
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMM");
        Date date = null;
        try {
            date = formatter.parse(dateTime);
            dateNowStr = sdf.format(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return dateNowStr;
    }

    /**
     * 格式化金额
     *
     * @param value
     * @return
     */
    public static String parseFenToYuan(String value) {
        if(TextUtils.isEmpty(value) || value.contentEquals("0") || value.contentEquals("0.00")) return "0.00";
        String moneyValue = value + "";
        try {
            moneyValue = (new BigDecimal(value).divide(new BigDecimal("100"))).setScale(2, BigDecimal.ROUND_DOWN).toString();
        } catch (Exception e) {
            moneyValue = new BigDecimal(value).setScale(2, BigDecimal.ROUND_DOWN).toString();
            e.printStackTrace();
        }
        return moneyValue;
    }

    /**
     * 格式化金额
     *
     * @param value
     * @return
     */
    public static String parseYuanToFen(String value) {
        if(TextUtils.isEmpty(value)) return "0";
        String moneyValue = value + "";
        try {
            moneyValue = (new BigDecimal(value)).multiply(new BigDecimal("100")).setScale(0, BigDecimal.ROUND_DOWN).toString();
        } catch (Exception e) {
            e.printStackTrace();
            moneyValue = new BigDecimal(value).setScale(0, BigDecimal.ROUND_DOWN).setScale(0, BigDecimal.ROUND_DOWN).toString();
        }
        return moneyValue;
    }

    /**
     * 格式化金额
     *
     * @param value
     * @return
     */
    public static String formatBigAmount(String value, boolean unitShow) {
        if(TextUtils.isEmpty(value)) return unitShow?"0.00元":"0.00";
        String moneyValue = value + "";
        try {
            if((new BigDecimal(value).compareTo(new BigDecimal("100000000"))) > 0) {
                moneyValue = fmtMicrometer((new BigDecimal(value).divide(new BigDecimal("100000000"))).setScale(2, BigDecimal.ROUND_DOWN).toString()) + "亿";
            } else if((new BigDecimal(value).compareTo(new BigDecimal("1000000"))) > 0) {
                moneyValue = fmtMicrometer((new BigDecimal(value).divide(new BigDecimal("10000"))).setScale(2, BigDecimal.ROUND_DOWN).toString()) + "万";
            } else {
                moneyValue = new BigDecimal(value).setScale(2, BigDecimal.ROUND_DOWN).toString();
                moneyValue = fmtMicrometer(moneyValue);
            }
        } catch (Exception e) {
            moneyValue = new BigDecimal(value).setScale(2, BigDecimal.ROUND_DOWN).toString();
            moneyValue = fmtMicrometer(moneyValue);
            e.printStackTrace();
        }
        return unitShow?(moneyValue+ "元"):moneyValue;
    }

    /**
     * 格式化金额
     *
     * @param value
     * @return
     */
    public static String formatBigWanAmount(String value, boolean unitShow) {
        if(TextUtils.isEmpty(value)) return unitShow?"0元":"0";
        String moneyValue = value + "";
        String moneyValue1 = new BigDecimal(value).setScale(2, BigDecimal.ROUND_DOWN).toString();
        try {
            if((new BigDecimal(value).compareTo(new BigDecimal("100000000"))) > 0) {
                moneyValue = fmtMicrometer((new BigDecimal(value).divide(new BigDecimal("100000000"))).setScale(2, BigDecimal.ROUND_DOWN).toString()) + "亿";
            } else if((new BigDecimal(value).compareTo(new BigDecimal("1000000"))) > 0) {
                moneyValue = fmtMicrometer((new BigDecimal(value).divide(new BigDecimal("10000"))).setScale(2, BigDecimal.ROUND_DOWN).toString()) + "万";
            } else {
                moneyValue = moneyValue1;
                moneyValue = fmtMicrometer(moneyValue);
            }
        } catch (Exception e) {
            moneyValue = moneyValue1;
            moneyValue = fmtMicrometer(moneyValue);
            e.printStackTrace();
        }
        return unitShow?(moneyValue+ "元"):moneyValue;
    }

    /**
     * @Description: 格式化数字为千分位
     * @param text
     * @return    设定文件
     * @return String    返回类型
     */
    public static String fmtMicrometer(String text) {
        DecimalFormat df = null;
        if (text.indexOf(".") > 0) {
            final int i = text.length() - text.indexOf(".") - 1;
            if (i == 0) {
                df = new DecimalFormat("###,##0.");
            } else if (i == 1) {
                df = new DecimalFormat("###,##0.0");
            } else {
                df = new DecimalFormat("###,##0.00");
            }
        } else {
            df = new DecimalFormat("###,##0");
        }
        double number = 0.0;
        try {
            number = Double.parseDouble(text);
        } catch (Exception e) {
            number = 0.0;
        }
        return df.format(number);
    }

    /**
     * 格式化终端台数
     *
     * @param value
     * @return
     */
    public static String formatBigTerminal(String value, boolean unitShow) {
        if(TextUtils.isEmpty(value)) return unitShow?"0台":"0";
        String moneyValue = value + "";
        Float money;
        try {
            money = Float.parseFloat(value);
            if(money >= 100000000) {
                moneyValue = fmtMicrometer(new BigDecimal(String.valueOf((money / 100000000))).setScale(0, BigDecimal.ROUND_DOWN).toString()) + "亿";
            } else if(money >= 1000000) {
                moneyValue = fmtMicrometer(new BigDecimal(String.valueOf((money / 10000))).setScale(0, BigDecimal.ROUND_DOWN).toString()) + "万";
            } else {
                moneyValue = new BigDecimal(String.valueOf((money))).setScale(0, BigDecimal.ROUND_DOWN).toString();
                moneyValue = fmtMicrometer(moneyValue);
            }
        } catch (Exception e) {
            moneyValue = new BigDecimal(value).setScale(0, BigDecimal.ROUND_DOWN).toString();
            moneyValue = fmtMicrometer(moneyValue);
            e.printStackTrace();
        }
        return unitShow?(moneyValue+ "台"):moneyValue;
    }

    /**
     * 格式化终端台数
     *
     * @param value
     * @return
     */
    public static String formatBigWanTerminal(String value, boolean unitShow) {
        if(TextUtils.isEmpty(value)) return unitShow?"0台":"0";
        String moneyValue = value + "";
        Float money;
        try {
            money = Float.parseFloat(value);
            if(money >= 100000000) {
                moneyValue = fmtMicrometer(new BigDecimal(String.valueOf((money / 100000000))).setScale(0, BigDecimal.ROUND_DOWN).toString()) + "亿";
            } else if(money >= 10000) {
                moneyValue = fmtMicrometer(new BigDecimal(String.valueOf((money / 10000))).setScale(0, BigDecimal.ROUND_DOWN).toString()) + "万";
            } else {
                moneyValue = new BigDecimal(String.valueOf((money))).setScale(0, BigDecimal.ROUND_DOWN).toString();
                moneyValue = fmtMicrometer(moneyValue);
            }
        } catch (Exception e) {
            moneyValue = new BigDecimal(value).setScale(0, BigDecimal.ROUND_DOWN).toString();
            moneyValue = fmtMicrometer(moneyValue);
            e.printStackTrace();
        }
        return unitShow?(moneyValue+ "台"):moneyValue;
    }

    /**
     * 格式化终端台数
     *
     * @param value
     * @return
     */
    public static String formatBigWanAmountNew(String value, boolean unitShow) {
        if(TextUtils.isEmpty(value)) return unitShow?"0元":"0";
        String moneyValue = value + "";
        String moneyValue1 = new BigDecimal(value).setScale(2, BigDecimal.ROUND_DOWN).toString();
        try {
            if((new BigDecimal(value).compareTo(new BigDecimal("100000000"))) > 0) {
                moneyValue = fmtMicrometer((new BigDecimal(value).divide(new BigDecimal("100000000"))).setScale(2, BigDecimal.ROUND_DOWN).toString()) + "亿";
            } else if((new BigDecimal(value).compareTo(new BigDecimal("10000"))) > 0) {
                moneyValue = fmtMicrometer((new BigDecimal(value).divide(new BigDecimal("10000"))).setScale(2, BigDecimal.ROUND_DOWN).toString()) + "万";
            } else {
                moneyValue = moneyValue1;
                moneyValue = fmtMicrometer(moneyValue);
            }
        } catch (Exception e) {
            moneyValue = moneyValue1;
            moneyValue = fmtMicrometer(moneyValue);
            e.printStackTrace();
        }
        return unitShow?(moneyValue+ "元"):moneyValue;
    }

    /**
     * 格式化渠道数量
     *
     * @param value
     * @return
     */
    public static String formatBigChannel(String value, boolean unitShow) {
        if(TextUtils.isEmpty(value)) return unitShow?"0人":"0";
        String moneyValue = value + "";
        Float money;
        try {
            money = Float.parseFloat(value);
            if(money >= 100000000) {
                moneyValue = fmtMicrometer(new BigDecimal(String.valueOf((money / 100000000))).setScale(0, BigDecimal.ROUND_DOWN).toString()) + "亿";
            } else if(money >= 1000000) {
                moneyValue = fmtMicrometer(new BigDecimal(String.valueOf((money / 10000))).setScale(0, BigDecimal.ROUND_DOWN).toString()) + "万";
            } else {
                moneyValue = new BigDecimal(String.valueOf((money))).setScale(0, BigDecimal.ROUND_DOWN).toString();
                moneyValue = fmtMicrometer(moneyValue);
            }
        } catch (Exception e) {
            moneyValue = new BigDecimal(value).setScale(0, BigDecimal.ROUND_DOWN).toString();
            moneyValue = fmtMicrometer(moneyValue);
            e.printStackTrace();
        }
        return unitShow?(moneyValue+ "人"):moneyValue;
    }

    /**
     * 格式化渠道数量
     *
     * @param value
     * @return
     */
    public static String formatBigWanChannel(String value, boolean unitShow) {
        if(TextUtils.isEmpty(value)) return unitShow?"0人":"0";
        String moneyValue = value + "";
        Float money;
        try {
            money = Float.parseFloat(value);
            if(money >= 100000000) {
                moneyValue = fmtMicrometer(new BigDecimal(String.valueOf((money / 100000000))).setScale(0, BigDecimal.ROUND_DOWN).toString()) + "亿";
            } else if(money >= 1000000) {
                moneyValue = fmtMicrometer(new BigDecimal(String.valueOf((money / 10000))).setScale(0, BigDecimal.ROUND_DOWN).toString()) + "万";
            } else {
                moneyValue = new BigDecimal(String.valueOf((money))).setScale(0, BigDecimal.ROUND_DOWN).toString();
                moneyValue = fmtMicrometer(moneyValue);
            }
        } catch (Exception e) {
            moneyValue = new BigDecimal(value).setScale(0, BigDecimal.ROUND_DOWN).toString();
            moneyValue = fmtMicrometer(moneyValue);
            e.printStackTrace();
        }
        return unitShow?(moneyValue+ "人"):moneyValue;
    }

}
