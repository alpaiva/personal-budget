package br.com.personal.budget.adapter.input.to;

import jakarta.annotation.Nonnull;

public record UserPwdTO (@Nonnull String email, @Nonnull String pwd) {
}
