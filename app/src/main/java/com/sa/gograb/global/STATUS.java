package com.sa.gograb.global;


import android.content.Context;


import com.sa.gograb.R;

import java.util.HashMap;
import java.util.Map;

public class STATUS {

    public static final String TAG = "STATUS";

    public enum VALIDATION {
        NEW(""),
        NOTHING("NOTHING"),
        LOGIN_FAILED("LOGIN_FAILED"),
        LOGIN_SUCCESS("LOGIN_SUCCESS"),
        REGISTER_FAILED("REGISTER_FAILED"),
        REGISTER_SUCCESS("REGISTER_SUCCESS"),
        OTP_FAILED("OTP_FAILED"),
        OTP_SUCCESS("OTP_SUCCESS"),
        NEED_VERIFICATION("NEED_VERIFICATION");

        private enum CODE {
            VALIDATION_NOTHING("600"),
            CODE_LOGIN_FAILED("601"),
            CODE_LOGIN_SUCCESS("602"),
            CODE_REGISTER_FAILED("603"),
            CODE_REGISTER_SUCCESS("604"),
            CODE_OTP_FAILED("605"),
            CODE_OTP_SUCCESS("606"),
            CODE_NEED_VERIFICATION("607");

            private final String code;

            private CODE(final String code) {
                this.code = code;
            }

            @Override
            public String toString() {
                return code;
            }

            public String toCode(){return this.code;}

            private static final Map<String, CODE> map = new HashMap<>();
            static {
                for (CODE en : values()) {
                    map.put(en.code, en);
                }
            }

            public static CODE valueFor(String name) {
                return map.get(name);
            }
        }

        private final String text;
        private VALIDATION(final String text) {
            this.text = text;
        }

        @Override
        public String toString() {
            return text;
        }

        public String getCode(){
            if(this.text.equals(LOGIN_SUCCESS.toString())){return CODE.CODE_LOGIN_SUCCESS.toCode();}
            else if(this.text.equals(LOGIN_FAILED.toString())){return CODE.CODE_LOGIN_FAILED.toCode();}
            else if(this.text.equals(REGISTER_FAILED.toString())){return CODE.CODE_REGISTER_FAILED.toCode();}
            else if(this.text.equals(REGISTER_SUCCESS.toString())){return CODE.CODE_REGISTER_SUCCESS.toCode();}
            else if(this.text.equals(OTP_FAILED.toString())){return CODE.CODE_OTP_FAILED.toCode();}
            else if(this.text.equals(OTP_SUCCESS.toString())){return CODE.CODE_OTP_SUCCESS.toCode();}
            else if(this.text.equals(NEED_VERIFICATION.toString())){return CODE.CODE_NEED_VERIFICATION.toCode();}
            else {return CODE.VALIDATION_NOTHING.toCode();}
        }

        public VALIDATION getValidation(String code){
            CODE codeEnum = CODE.VALIDATION_NOTHING;
            try{
                codeEnum = CODE.valueFor(code);
            }catch (Exception ex){
                codeEnum = CODE.VALIDATION_NOTHING;
            }
            if(codeEnum!=null){
                switch (codeEnum){
                    case CODE_LOGIN_FAILED:
                        return LOGIN_FAILED;
                    case CODE_LOGIN_SUCCESS:
                        return LOGIN_SUCCESS;
                    case CODE_REGISTER_FAILED:
                        return REGISTER_FAILED;
                    case CODE_REGISTER_SUCCESS:
                        return REGISTER_SUCCESS;
                    case CODE_OTP_FAILED:
                        return OTP_FAILED;
                    case CODE_OTP_SUCCESS:
                        return OTP_SUCCESS;
                    case CODE_NEED_VERIFICATION:MPLETED:
                        return NEED_VERIFICATION;
                    default :
                        return NOTHING;
                }
            }else{
                return NOTHING;
            }
        }
    }

    public enum QUOTE {
        NEW(""),
        NOTHING("QUOTE_NOTHING"),
         INIT("QUOTE_INIT"),
        DECLINED("QUOTE_DECLINED"),
        WAITING("QUOTE_WAITING"),
        PROCESSING("QUOTE_PROCESSING"),
        COMPLETED("QUOTE_COMPLETED");

        private enum CODE {
            QUOTE_NOTHING("100"),
            QUOTE_INIT("101"),
            QUOTE_DECLINED("102"),
            QUOTE_WAITING("103"),
            QUOTE_PROCESSING("104"),
            QUOTE_COMPLETED("105");

            private final String code;

            private CODE(final String code) {
                this.code = code;
            }

            @Override
            public String toString() {
                return code;
            }

            public String toCode(){return this.code;}

            private static final Map<String, CODE> map = new HashMap<>();
            static {
                for (CODE en : values()) {
                    map.put(en.code, en);
                }
            }

            public static CODE valueFor(String name) {
                return map.get(name);
            }
        }

        private final String text;
        private QUOTE(final String text) {
            this.text = text;
        }

        @Override
        public String toString() {
            return text;
        }

        public String getCode(){
            if(this.text.equals(INIT.toString())){return CODE.QUOTE_INIT.toCode();}
            else if(this.text.equals(DECLINED.toString())){return CODE.QUOTE_DECLINED.toCode();}
            else if(this.text.equals(WAITING.toString())){return CODE.QUOTE_WAITING.toCode();}
            else if(this.text.equals(PROCESSING.toString())){return CODE.QUOTE_PROCESSING.toCode();}
            else if(this.text.equals(COMPLETED.toString())){return CODE.QUOTE_COMPLETED.toCode();}
            else {return CODE.QUOTE_NOTHING.toCode();}
        }

        public QUOTE getQuote(String code){
            CODE codeEnum = CODE.QUOTE_NOTHING;
            try{
                codeEnum = CODE.valueFor(code);
            }catch (Exception ex){
                codeEnum = CODE.QUOTE_NOTHING;
            }
            if(codeEnum!=null){
                switch (codeEnum){
                    case QUOTE_INIT:
                        return INIT;
                    case QUOTE_DECLINED:
                        return DECLINED;
                    case QUOTE_WAITING:
                        return WAITING;
                    case QUOTE_PROCESSING:
                        return PROCESSING;
                    case QUOTE_COMPLETED:
                        return COMPLETED;
                    default :
                        return NOTHING;
                }
            }else{
                return NOTHING;
            }
        }
    }

    public enum PAYMENT_MODE {
        NEW(""),
        NOTHING("PAYMENT_MODE_NOTHING"),
        COD("PAYMENT_MODE_COD"),
        PAY_NOW("PAYMENT_MODE_PAY_NOW");

        private enum CODE {
            PAYMENT_MODE_NOTHING("600"),
            PAYMENT_MODE_COD("601"),
            PAYMENT_MODE_PAY_NOW("602");

            private final String code;

            private CODE(final String code) {
                this.code = code;
            }

            @Override
            public String toString() {
                return code;
            }

            public String toCode(){return this.code;}

            private static final Map<String, CODE> map = new HashMap<>();
            static {
                for (CODE en : values()) {
                    map.put(en.code, en);
                }
            }

            public static CODE valueFor(String name) {
                return map.get(name);
            }
        }

        private final String text;
        private PAYMENT_MODE(final String text) {
            this.text = text;
        }

        @Override
        public String toString() {
            return text;
        }

        public String getCode(){
            if(this.text.equals(COD.toString())){return CODE.PAYMENT_MODE_COD.toCode();}
            else if(this.text.equals(PAY_NOW.toString())){return CODE.PAYMENT_MODE_PAY_NOW.toCode();}
            else {return CODE.PAYMENT_MODE_NOTHING.toCode();}
        }

        public PAYMENT_MODE getPaymentMode(String code){
            CODE codeEnum = CODE.PAYMENT_MODE_NOTHING;
            try{
                codeEnum = CODE.valueFor(code);
            }catch (Exception ex){
                codeEnum = CODE.PAYMENT_MODE_NOTHING;
            }
            if(codeEnum!=null){
                switch (codeEnum){
                    case PAYMENT_MODE_PAY_NOW:
                        return PAY_NOW;
                    case PAYMENT_MODE_COD:
                        return COD;
                    default :
                        return NOTHING;
                }
            }else{
                return NOTHING;
            }
        }
    }

    public enum BID {
        NEW(""),
        NOTHING("BID_NOTHING"),
        INIT("BID_INIT"),
        ACCEPTED("BID_ACCEPTED"),
        DECLINED("BID_DECLINED"),
        WAITING("BID_WAITING"),
        PROCESSING("BID_PROCESSING"),
        COMPLETED("BID_COMPLETED");

        private enum CODE {
            BID_NOTHING("200"),
            BID_INIT("201"),
            BID_ACCEPTED("203"),
            BID_DECLINED("204"),
            BID_WAITING("205"),
            BID_PROCESSING("206"),
            BID_COMPLETED("202");

            private final String code;

            private CODE(final String code) {
                this.code = code;
            }

            @Override
            public String toString() {
                return code;
            }

            public String toCode(){return this.code;}

            private static final Map<String, CODE> map = new HashMap<>();
            static {
                for (CODE en : values()) {
                    map.put(en.code, en);
                }
            }

            public static CODE valueFor(String name) {
                return map.get(name);
            }
        }
        private final String text;

        private BID(final String text) {
            this.text = text;
        }

        @Override
        public String toString() {
            return text;
        }

        public String getCode(){
            if(this.text.equals(INIT.toString())){return CODE.BID_INIT.toCode();}
            else if(this.text.equals(ACCEPTED.toString())){return CODE.BID_ACCEPTED.toCode();}
            else if(this.text.equals(DECLINED.toString())){return CODE.BID_DECLINED.toCode();}
            else if(this.text.equals(WAITING.toString())){return CODE.BID_WAITING.toCode();}
            else if(this.text.equals(PROCESSING.toString())){return CODE.BID_PROCESSING.toCode();}
            else if(this.text.equals(COMPLETED.toString())){return CODE.BID_COMPLETED.toCode();}
            else {return CODE.BID_NOTHING.toCode();}
        }

        public BID getBID(String code){
            CODE codeEnum = CODE.BID_NOTHING;
            try{
                codeEnum = CODE.valueFor(code);
            }catch (Exception ex){
                codeEnum = CODE.BID_NOTHING;
            }
            if(codeEnum!=null){
                switch (codeEnum){
                    case BID_INIT:
                        return INIT;
                    case BID_ACCEPTED:
                        return ACCEPTED;
                    case BID_DECLINED:
                        return DECLINED;
                    case BID_WAITING:
                        return WAITING;
                    case BID_PROCESSING:
                        return PROCESSING;
                    case BID_COMPLETED:
                        return COMPLETED;
                    default :
                        return NOTHING;
                }
            }else{
                return NOTHING;
            }

        }
    }

    public enum CART {
        NOTHING("CART_NOTHING"),
        INIT("CART_INIT"),
        DECLINED("CART_DECLINED"),
        PROCESSING("CART_PROCESSING");

        private enum CODE {
            CART_NOTHING(300),
            CART_INIT(301),
            CART_DECLINED(303),
            CART_PROCESSING(302);

            private final int code;

            private CODE(final int code) {
                this.code = code;
            }

            @Override
            public String toString() {
                return code+"";
            }

            public int toCode(){return this.code;}
        }

        private final String text;

        private CART(final String text) {
            this.text = text;
        }

        @Override
        public String toString() {
            return text;
        }

        public int getCode(){
            if(this.text.equals(INIT.toString())){return CODE.CART_INIT.toCode();}
            else if(this.text.equals(DECLINED.toString())){return CODE.CART_DECLINED.toCode();}
            else if(this.text.equals(PROCESSING.toString())){return CODE.CART_PROCESSING.toCode();}
            else {return CODE.CART_NOTHING.toCode();}
        }

        public String getName(int code){
            CODE codeEnum = CODE.valueOf(code+"");
            switch (codeEnum){
                case CART_INIT:
                    return INIT.toString();
                case CART_DECLINED:
                    return DECLINED.toString();
                case CART_PROCESSING:
                    return PROCESSING.toString();
                default :
                    return NOTHING.toString();
            }
        }
    }

    public enum TRANS {
        NEW(""),
        NOTHING("TRANS_NOTHING"),
        INIT("TRANS_INIT"),
        ACCEPTED("TRANS_ACCEPT"),
        DECLINED("TRANS_DECLINED"),
        WAITING("TRANS_WAITING"),
        PROCESSING("TRANS_PROCESSING"),
        CANCELLED("TRANS_CANCELLED"),
        COMPLETED("TRANS_COMPLETED");

        private enum CODE {
            TRANS_NOTHING("400"),
            TRANS_INIT("401"),
            TRANS_ACCEPT("404"),
            TRANS_DECLINED("405"),
            TRANS_WAITING("403"),
            TRANS_PROCESSING("402"),
            TRANS_CANCELLED("407"),
            TRANS_COMPLETED("406");

            private final String code;

            private CODE(final String code) {
                this.code = code;
            }

            @Override
            public String toString() {
                return code+"";
            }

            public String toCode(){return this.code;}

            private static final Map<String, CODE> map = new HashMap<>();
            static {
                for (CODE en : values()) {
                    map.put(en.code, en);
                }
            }

            public static CODE valueFor(String name) {
                return map.get(name);
            }
        }

        private final String text;

        private TRANS(final String text) {
            this.text = text;
        }

        @Override
        public String toString() {
            return text;
        }

        public String getCode(){
            if(this.text.equals(INIT.toString())){return CODE.TRANS_INIT.toCode();}
            else if(this.text.equals(ACCEPTED.toString())){return CODE.TRANS_ACCEPT.toCode();}
            else if(this.text.equals(DECLINED.toString())){return CODE.TRANS_DECLINED.toCode();}
            else if(this.text.equals(WAITING.toString())){return CODE.TRANS_WAITING.toCode();}
            else if(this.text.equals(PROCESSING.toString())){return CODE.TRANS_PROCESSING.toCode();}
            else if(this.text.equals(COMPLETED.toString())){return CODE.TRANS_COMPLETED.toCode();}
            else if(this.text.equals(CANCELLED.toString())){return CODE.TRANS_CANCELLED.toCode();}
            else {return CODE.TRANS_NOTHING.toCode();}
        }

        public TRANS getTRANS(String code){
            CODE codeEnum = CODE.TRANS_NOTHING;
            try{
                codeEnum = CODE.valueFor(code);
            }catch (Exception ex){
                codeEnum = CODE.TRANS_NOTHING;
            }
            if(codeEnum!=null){
                switch (codeEnum){
                    case TRANS_INIT:
                        return INIT;
                    case TRANS_ACCEPT:
                        return ACCEPTED;
                    case TRANS_COMPLETED:
                        return COMPLETED;
                    case TRANS_DECLINED:
                        return DECLINED;
                    case TRANS_PROCESSING:
                        return PROCESSING;
                    case TRANS_WAITING:
                        return WAITING;
                    case TRANS_CANCELLED:
                        return CANCELLED;
                    default :
                        return NOTHING;
                }
            }else{
                return NOTHING;
            }

        }
    }

    public enum ORDER {
        NEW(""),
        ORDER_RECEIVED("ORDER_RECEIVED"),
        PAYMENT_RECEIVED("PAYMENT_RECEIVED"),
        ORDER_PACKED("ORDER_PACKED"),
        ORDER_SHIPPED("ORDER_SHIPPED"),
        ORDER_DELIVERED("ORDER_DELIVERED");

        private static final Map<String,ORDER> map = new HashMap<>();
        static {
            for (ORDER en : values()) {
                map.put(en.text, en);
            }
        }

        public static ORDER valueFor(String name) {
            return map.get(name);
        }

        private enum CODE {
            CODE_NEW(""),
            CODE_ORDER_RECEIVED("4"),
            CODE_PAYMENT_RECEIVED("5"),
            CODE_ORDER_PACKED("6"),
            CODE_SHIPPED("7"),
            CODE_DELIVERED("8");

            private final String code;

            private CODE(final String code) {
                this.code = code;
            }

            @Override
            public String toString() {
                return code+"";
            }

            public String toCode(){return this.code;}

            private static final Map<String, CODE> map = new HashMap<>();
            static {
                for (CODE en : values()) {
                    map.put(en.code, en);
                }
            }

            public static CODE valueFor(String name) {
                return map.get(name);
            }
        }

        private final String text;

        private ORDER(final String text) {
            this.text = text;
        }

        @Override
        public String toString() {
            return text;
        }

        public String getCode(){
            if(this.text.equals(ORDER_RECEIVED.toString())){return CODE.CODE_ORDER_RECEIVED.toCode();}
            else if(this.text.equals(PAYMENT_RECEIVED.toString())){return CODE.CODE_PAYMENT_RECEIVED.toCode();}
            else if(this.text.equals(ORDER_PACKED.toString())){return CODE.CODE_ORDER_PACKED.toCode();}
            else if(this.text.equals(ORDER_SHIPPED.toString())){return CODE.CODE_SHIPPED.toCode();}
            else if(this.text.equals(ORDER_DELIVERED.toString())){return CODE.CODE_DELIVERED.toCode();}
            else {return CODE.CODE_NEW.toCode();}
        }

        public ORDER getORDER_TYPE(String code){
            CODE codeEnum = CODE.CODE_NEW;
            try{
                codeEnum = CODE.valueFor(code);
            }catch (Exception ex){
                codeEnum = CODE.CODE_NEW;
            }
            if(codeEnum!=null){
                switch (codeEnum){
                    case CODE_ORDER_RECEIVED:
                        return ORDER_RECEIVED;
                    case CODE_PAYMENT_RECEIVED:
                        return PAYMENT_RECEIVED;
                    case CODE_ORDER_PACKED:
                        return ORDER_PACKED;
                    case CODE_SHIPPED:
                        return ORDER_SHIPPED;
                    case CODE_DELIVERED:
                        return ORDER_DELIVERED;
                    default :
                        return NEW;
                }
            }else{
                return NEW;
            }

        }

       /* public ORDER getORDER_TYPEFromName(Context context, String name){
            ORDER codeEnum = NEW;
            if(name.equals(context.getString(R.string.order_received))){codeEnum = ORDER_RECEIVED;}
            else if(name.equals(context.getString(R.string.payment_received))){codeEnum = PAYMENT_RECEIVED;}
            else if(name.equals(context.getString(R.string.order_packed))){codeEnum = ORDER_PACKED;}
            else if(name.equals(context.getString(R.string.order_shipped))){codeEnum = ORDER_SHIPPED;}
            //else if(name.equals(context.getString(R.string.order_delivered))){codeEnum = ORDER_DELIVERED;}
            return codeEnum;
        }


        public int getResource(){
            if(this.text.equals(NEW.toString())){return R.string.order_received;}
            else if(this.text.equals(ORDER_RECEIVED.toString())){return R.string.order_received;}
            else if(this.text.equals(PAYMENT_RECEIVED.toString())){return R.string.payment_received;}
            else if(this.text.equals(ORDER_PACKED.toString())){return R.string.order_packed;}
            else if(this.text.equals(ORDER_SHIPPED.toString())){return R.string.order_shipped;}
            //else if(this.text.equals(ORDER_DELIVERED.toString())){return R.string.order_delivered;}
            else{return R.string.support_status_initiated;}
        }*/

       /* public int getColorResource(){
            if(this.text.equals(NEW.toString())){return GlobalFunctions.getColor(R.color.red);}
            else if(this.text.equals(ORDER_RECEIVED.toString())){return GlobalFunctions.getColor(R.color.orange);}
            else if(this.text.equals(PAYMENT_RECEIVED.toString())){return GlobalFunctions.getColor(R.color.yellow);}
            else if(this.text.equals(ORDER_PACKED.toString())){return GlobalFunctions.getColor(R.color.yellow);}
            else if(this.text.equals(ORDER_SHIPPED.toString())){return GlobalFunctions.getColor(R.color.green);}
            else if(this.text.equals(ORDER_DELIVERED.toString())){return GlobalFunctions.getColor(R.color.green_light);}
            else{return GlobalFunctions.getColor(R.color.red);}
        }*/

    }

    public enum NOTIFICATION_TYPE {
        GENERAL("GENERAL"),
        OFFER("OFFER"),
        ORDER("ORDER");

        private enum CODE {
            NOTIFICATION_TYPE_GENERAL("1"),
            NOTIFICATION_TYPE_OFFER("2"),
            NOTIFICATION_TYPE_ORDER("3");

            private final String code;

            private CODE(final String code) {
                this.code = code;
            }

            @Override
            public String toString() {
                return code;
            }

            public String toCode(){return this.code;}

            private static final Map<String, CODE> map = new HashMap<>();
            static {
                for (CODE en : values()) {
                    map.put(en.code, en);
                }
            }

            public static CODE valueFor(String name) {
                return map.get(name);
            }
        }

        private final String text;
        private NOTIFICATION_TYPE(final String text) {
            this.text = text;
        }

        @Override
        public String toString() {
            return text;
        }

        public String getCode(){
            if(this.text.equals(OFFER.toString())){return CODE.NOTIFICATION_TYPE_OFFER.toCode();}
            else if(this.text.equals(ORDER.toString())){return CODE.NOTIFICATION_TYPE_ORDER.toCode();}
            else {return CODE.NOTIFICATION_TYPE_GENERAL.toCode();}
        }

        public NOTIFICATION_TYPE getNotificationType(String code){
            CODE codeEnum = CODE.NOTIFICATION_TYPE_GENERAL;
            try{
                codeEnum = CODE.valueFor(code);
            }catch (Exception ex){
                codeEnum = CODE.NOTIFICATION_TYPE_GENERAL;
            }
            if(codeEnum!=null){
                switch (codeEnum){
                    case NOTIFICATION_TYPE_OFFER:
                        return OFFER;
                    case NOTIFICATION_TYPE_ORDER:
                        return ORDER;
                    default :
                        return GENERAL;
                }
            }else{
                return GENERAL;
            }
        }
    }

}
