import { describe, it, expect, beforeEach, vi } from 'vitest';
import { render, fireEvent, screen } from '@testing-library/react';
import { ThemeToggle } from './ThemeToggle';
import React from 'react';

describe('ThemeToggle', () => {
  beforeEach(() => {
    // Clear document classList
    document.documentElement.classList.remove('light');
    // Clear localStorage
    localStorage.clear();
    vi.clearAllMocks();
  });

  it('renders with dark theme by default', () => {
    render(<ThemeToggle />);
    expect(document.documentElement.classList.contains('light')).toBe(false);
    // Dark theme should show Moon icon (simplified check by aria-label since icons are complex)
    expect(screen.getByLabelText('Toggle Theme')).toBeDefined();
  });

  it('initializes with light theme if saved in localStorage', () => {
    localStorage.setItem('neon_occult_theme', 'light');
    render(<ThemeToggle />);
    expect(document.documentElement.classList.contains('light')).toBe(true);
  });

  it('toggles from dark to light on click', () => {
    render(<ThemeToggle />);
    const button = screen.getByLabelText('Toggle Theme');

    fireEvent.click(button);

    expect(document.documentElement.classList.contains('light')).toBe(true);
    expect(localStorage.getItem('neon_occult_theme')).toBe('light');
  });

  it('toggles from light to dark on click', () => {
    localStorage.setItem('neon_occult_theme', 'light');
    render(<ThemeToggle />);
    const button = screen.getByLabelText('Toggle Theme');

    // First confirm it started light
    expect(document.documentElement.classList.contains('light')).toBe(true);

    fireEvent.click(button);

    expect(document.documentElement.classList.contains('light')).toBe(false);
    expect(localStorage.getItem('neon_occult_theme')).toBe('dark');
  });
});
