package com.example.myapplication07.bean;

import com.example.myapplication07.R;

import java.util.ArrayList;

public class GoodsInfo {
    public long rowid; // 行号
    public int sn; // 序号
    public String name; // 名称
    public String desc; // 描述
    public float price; // 价格
    public String thumb_path; // 小图的保存路径
    public String pic_path; // 大图的保存路径
    public int thumb; // 小图的资源编号
    public int pic; // 大图的资源编号

    public GoodsInfo() {
        rowid = 0L;
        sn = 0;
        name = "";
        desc = "";
        price = 0;
        thumb_path = "";
        pic_path = "";
        thumb = 0;
        pic = 0;
    }

    // 声明一个手机商品的名称数组
    private static String[] mNameArray = {
            "仿貂绒毛衣女", "复古印花夹棉外套", "格子中长款羊羔毛", "羽绒服外套女", "加厚慵懒风外套", "针织短款宽松外穿","外穿针织开衫外套","波点半身裙"
    };
    // 声明一个手机商品的描述数组
    private static String[] mDescArray = {
            "阔色甜美可爱仿貂绒毛衣女宽松套头针织衫上衣",
            "阔色女装复古印花夹棉外套工装风加厚棉衣棉服",
            "阔色加绒加厚格子中长款羊羔毛小个子上衣",
            "阔色宽松毛领羽绒服外套女休闲ins潮面包服",
            "阔色马甲背心外套女外搭秋冬棉服宽松韩版棉衣",
            "阔色古着毛衣女针织短款宽松外穿开衫加厚慵懒风",
            "阔色毛衣女宽松秋冬加厚外穿针织开衫外套韩系v领",

            "阔色韩版宽松卫衣时尚套装女长款a字波点半身裙"

    };
    // 声明一个手机商品的价格数组
    private static float[] mPriceArray = {258, 136, 375, 264, 174, 325,358,157};
    // 声明一个手机商品的小图数组
    private static int[] mThumbArray = {
            R.drawable.cloth1_s, R.drawable.cloth2_s, R.drawable.cloth3_s,
            R.drawable.cloth4_s, R.drawable.cloth5_s, R.drawable.cloth6_s,
            R.drawable.cloth7_s,R.drawable.cloth9_s

    };
    // 声明一个手机商品的大图数组
    private static int[] mPicArray = {
            R.drawable.cloth1, R.drawable.cloth2, R.drawable.cloth3,
            R.drawable.cloth4, R.drawable.cloth5, R.drawable.cloth6,
            R.drawable.cloth7,R.drawable.cloth9

    };

    // 获取默认的手机信息列表
    public static ArrayList<GoodsInfo> getDefaultList() {
        ArrayList<GoodsInfo> goodsList = new ArrayList<GoodsInfo>();
        for (int i = 0; i < mNameArray.length; i++) {
            GoodsInfo info = new GoodsInfo();
            info.name = mNameArray[i];
            info.desc = mDescArray[i];
            info.price = mPriceArray[i];
            info.thumb = mThumbArray[i];
            info.pic = mPicArray[i];
            goodsList.add(info);
        }
        return goodsList;
    }
}
