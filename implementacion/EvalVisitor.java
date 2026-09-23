public class EvalVisitor extends CalculadoraBaseVisitor<Integer> {

    @Override
    public Integer visitPrintCaso1(CalculadoraParser.PrintCaso1Context ctx) {
        int val = visit(ctx.expr1());
        System.out.println("[Caso 1 | Mult Alta, Izquierda] " + ctx.expr1().getText() + " = " + val);
        return val;
    }

    @Override
    public Integer visitPrintCaso2(CalculadoraParser.PrintCaso2Context ctx) {
        int val = visit(ctx.expr2());
        System.out.println("[Caso 2 | Suma Alta, Izquierda] " + ctx.expr2().getText() + " = " + val);
        return val;
    }

    @Override
    public Integer visitPrintCaso3(CalculadoraParser.PrintCaso3Context ctx) {
        int val = visit(ctx.expr3());
        System.out.println("[Caso 3 | Mult Alta, Derecha]   " + ctx.expr3().getText() + " = " + val);
        return val;
    }

    @Override
    public Integer visitPrintCaso4(CalculadoraParser.PrintCaso4Context ctx) {
        int val = visit(ctx.expr4());
        System.out.println("[Caso 4 | Suma Alta, Derecha]   " + ctx.expr4().getText() + " = " + val);
        return val;
    }

    // --- Caso 1 ---
    @Override
    public Integer visitMulDiv1(CalculadoraParser.MulDiv1Context ctx) {
        int left = visit(ctx.expr1(0));
        int right = visit(ctx.expr1(1));
        return ctx.op.getText().equals("*") ? left * right : left / right;
    }

    @Override
    public Integer visitAddSub1(CalculadoraParser.AddSub1Context ctx) {
        int left = visit(ctx.expr1(0));
        int right = visit(ctx.expr1(1));
        return ctx.op.getText().equals("+") ? left + right : left - right;
    }

    @Override
    public Integer visitInt1(CalculadoraParser.Int1Context ctx) {
        return Integer.parseInt(ctx.INT().getText());
    }

    @Override
    public Integer visitParens1(CalculadoraParser.Parens1Context ctx) {
        return visit(ctx.expr1());
    }

    // --- Caso 2 ---
    @Override
    public Integer visitAddSub2(CalculadoraParser.AddSub2Context ctx) {
        int left = visit(ctx.expr2(0));
        int right = visit(ctx.expr2(1));
        return ctx.op.getText().equals("+") ? left + right : left - right;
    }

    @Override
    public Integer visitMulDiv2(CalculadoraParser.MulDiv2Context ctx) {
        int left = visit(ctx.expr2(0));
        int right = visit(ctx.expr2(1));
        return ctx.op.getText().equals("*") ? left * right : left / right;
    }

    @Override
    public Integer visitInt2(CalculadoraParser.Int2Context ctx) {
        return Integer.parseInt(ctx.INT().getText());
    }

    @Override
    public Integer visitParens2(CalculadoraParser.Parens2Context ctx) {
        return visit(ctx.expr2());
    }

    // --- Caso 3 ---
    @Override
    public Integer visitMulDiv3(CalculadoraParser.MulDiv3Context ctx) {
        int left = visit(ctx.expr3(0));
        int right = visit(ctx.expr3(1));
        return ctx.op.getText().equals("*") ? left * right : left / right;
    }

    @Override
    public Integer visitAddSub3(CalculadoraParser.AddSub3Context ctx) {
        int left = visit(ctx.expr3(0));
        int right = visit(ctx.expr3(1));
        return ctx.op.getText().equals("+") ? left + right : left - right;
    }

    @Override
    public Integer visitInt3(CalculadoraParser.Int3Context ctx) {
        return Integer.parseInt(ctx.INT().getText());
    }

    @Override
    public Integer visitParens3(CalculadoraParser.Parens3Context ctx) {
        return visit(ctx.expr3());
    }

    // --- Caso 4 ---
    @Override
    public Integer visitAddSub4(CalculadoraParser.AddSub4Context ctx) {
        int left = visit(ctx.expr4(0));
        int right = visit(ctx.expr4(1));
        return ctx.op.getText().equals("+") ? left + right : left - right;
    }

    @Override
    public Integer visitMulDiv4(CalculadoraParser.MulDiv4Context ctx) {
        int left = visit(ctx.expr4(0));
        int right = visit(ctx.expr4(1));
        return ctx.op.getText().equals("*") ? left * right : left / right;
    }

    @Override
    public Integer visitInt4(CalculadoraParser.Int4Context ctx) {
        return Integer.parseInt(ctx.INT().getText());
    }

    @Override
    public Integer visitParens4(CalculadoraParser.Parens4Context ctx) {
        return visit(ctx.expr4());
    }
}
