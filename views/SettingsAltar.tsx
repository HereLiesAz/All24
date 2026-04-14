
import React, { useState, useEffect } from 'react';
import { Home, Save } from 'lucide-react';

export const SettingsAltar: React.FC = () => {
  const [hotelAddress, setHotelAddress] = useState('');
  const [geminiApiKey, setGeminiApiKey] = useState('');
  const [isSaved, setIsSaved] = useState(false);

  useEffect(() => {
    const settingsStr = localStorage.getItem('neon_occult_settings');
    if (settingsStr) {
      const settings = JSON.parse(settingsStr);
      setHotelAddress(settings.hotelAddress || '');
      setGeminiApiKey(settings.geminiApiKey || '');
    }
  }, []);

  const handleSave = () => {
    localStorage.setItem('neon_occult_settings', JSON.stringify({ hotelAddress, geminiApiKey }));
    setIsSaved(true);
    setTimeout(() => setIsSaved(false), 3000);
  };

  return (
    <div className="py-12 space-y-12 animate-in fade-in slide-in-from-top-4 duration-1000">
      <div className="text-center">
        <div className="flex justify-center mb-8">
          <div className="w-20 h-20 bg-black/50 border-2 border-[var(--accent-p)] flex items-center justify-center text-[var(--accent-p)] shadow-[0_0_30px_rgba(239,68,68,0.3)]">
            <Home size={32} className="pixel-icon" strokeWidth={2} />
          </div>
        </div>
        <h2 className="font-glitch text-5xl mb-3 text-white uppercase tracking-tight">THE ALTAR</h2>
        <p className="text-white/70 text-sm font-mono italic max-w-xs mx-auto px-4">
          "Bind your physical essence to these coordinates so the spirits may find you a path home."
        </p>
      </div>

      <div className="bg-[var(--card-bg)] p-10 border-t border-[var(--accent-c)] space-y-8 relative overflow-hidden transition-colors shadow-2xl backdrop-blur-md">
        <div className="absolute top-0 right-0 w-40 h-40 bg-[var(--accent-c)]/10 blur-[60px] pointer-events-none" />

        <div className="space-y-4">
          <label className="text-[12px] font-mono text-[var(--accent-c)] uppercase tracking-[0.2em] block font-bold border-b border-white/10 pb-2">SANCTUARY COORDINATES</label>
          <input
            type="text"
            value={hotelAddress}
            onChange={(e) => setHotelAddress(e.target.value)}
            placeholder="e.g. 123 Royal St, NOLA"
            className="w-full bg-black/60 border border-white/20 p-4 text-sm focus:outline-none focus:border-[var(--accent-p)] transition-colors font-mono text-white placeholder:text-white/30"
          />
        </div>

        <div className="space-y-4">
          <label className="text-[12px] font-mono text-[var(--accent-c)] uppercase tracking-[0.2em] block font-bold border-b border-white/10 pb-2">GEMINI ORACLE KEY</label>
          <input
            type="password"
            value={geminiApiKey}
            onChange={(e) => setGeminiApiKey(e.target.value)}
            placeholder="Enter your Gemini API key"
            className="w-full bg-black/60 border border-white/20 p-4 text-sm focus:outline-none focus:border-[var(--accent-p)] transition-colors font-mono text-white placeholder:text-white/30"
          />
          <p className="text-[10px] font-mono text-white/40 italic">
            "Your key remains local, whispered only to the spirits in your browser."
          </p>
        </div>

        <button
          onClick={handleSave}
          className="w-full py-4 bg-[var(--accent-c)] hover:bg-[var(--accent-p)] text-black font-glitch text-2xl tracking-[0.1em] flex items-center justify-center gap-4 transition-all active:scale-95 shadow-lg uppercase"
        >
          {isSaved ? "COORDINATES BINDED" : <><Save size={24} className="pixel-icon" strokeWidth={2} /> BIND TO ALTAR</>}
        </button>
      </div>
    </div>
  );
};
