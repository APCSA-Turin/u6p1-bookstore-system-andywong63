package com.example.project;

public class ANSI {
    // https://stackoverflow.com/a/33206814
    // https://i.sstatic.net/9UVnC.png
    String string;
    String code = "";

    public ANSI(String string) {
        this.string = string;
    }

    // Convert to string so it can be printed
    public String toString() {
        return "\033[" + code + "m" + string + "\033[0m";
    }

    public ANSI reset() {
        code = "";
        return this;
    }
    public ANSI bold() {
        return add("1");
    }
    public ANSI faint() {
        return add("2");
    }
    public ANSI italic() {
        return add("3");
    }
    public ANSI underline() {
        return add("4");
    }
    public ANSI strikethrough() {
        return add("9");
    }
    public ANSI black() {
        return add("30");
    }
    public ANSI red() {
        return add("31");
    }
    public ANSI green() {
        return add("32");
    }
    public ANSI yellow() {
        return add("33");
    }
    public ANSI blue() {
        return add("34");
    }
    public ANSI magenta() {
        return add("35");
    }
    public ANSI cyan() {
        return add("36");
    }
    public ANSI white() {
        return add("37");
    }


    private ANSI add(String addCode) {
        if (addCode.length() == 0) {
            code = addCode;
        } else {
            code += ";" + addCode;
        }
        return this;
    }

    public static ANSI bold(String str) {
        return new ANSI(str).bold();
    }
    public static ANSI faint(String str) {
        return new ANSI(str).faint();
    }
    public static ANSI italic(String str) {
        return new ANSI(str).italic();
    }
    public static ANSI underline(String str) {
        return new ANSI(str).underline();
    }
    public static ANSI strikethrough(String str) {
        return new ANSI(str).strikethrough();
    }
    public static ANSI black(String str) {
        return new ANSI(str).black();
    }
    public static ANSI red(String str) {
        return new ANSI(str).red();
    }
    public static ANSI green(String str) {
        return new ANSI(str).green();
    }
    public static ANSI yellow(String str) {
        return new ANSI(str).yellow();
    }
    public static ANSI blue(String str) {
        return new ANSI(str).blue();
    }
    public static ANSI magenta(String str) {
        return new ANSI(str).magenta();
    }
    public static ANSI cyan(String str) {
        return new ANSI(str).cyan();
    }
    public static ANSI white(String str) {
        return new ANSI(str).white();
    }

    public static void clearScreen() { // https://stackoverflow.com/a/32295974
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }
}
