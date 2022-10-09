package io.github.dayal96.absyn.transform;

import io.github.dayal96.absyn.Absyn;
import io.github.dayal96.expression.local.LocalDefinition;
import java.util.List;
import java.util.stream.Collectors;

public class DeclarationToLocalDef extends PartialAbsynVisitor<List<LocalDefinition>> {

  private static final DeclarationToLocalDef instance = new DeclarationToLocalDef();

  public static DeclarationToLocalDef getInstance() { return instance; }

  private DeclarationToLocalDef() {
    super(new UnsupportedOperationException("Cannot be converted into LocalDefinition"));
  }

  @Override
  public List<LocalDefinition> visitDecl(String id, Absyn expr) {
    return List.of(new LocalDefinition(id, expr.accept(SimpleAbsynToExpr.getInstance())));
  }

  @Override
  public List<LocalDefinition> visitDecList(List<Absyn> decList) {
    return decList.stream().flatMap((decl) -> decl.accept(this).stream())
        .collect(Collectors.toList());
  }
}
